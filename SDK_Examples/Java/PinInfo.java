import pt.gov.cartaodecidadao.*;
import java.util.Scanner;


public class PinInfo {

    //This static block is needed to load the SDK library
    static {
        try {
            System.loadLibrary("pteidlibj");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load. \n" + e);
            System.exit(1);
        }
    }
    
    //Main attributes needed for SDK functionalities
    PTEID_ReaderSet readerSet = null;
    PTEID_ReaderContext readerContext = null;
    PTEID_EIDCard eidCard = null;
    PTEID_EId eid = null;
    PTEID_CardType cardType = null;
    PTEID_CardContactInterface contactInterface = null;

    /**
     * Initializes the SDK and sets main variables
     * @throws PTEID_Exception when there is some error with the SDK methods
     */
    public void initiate() throws PTEID_Exception {
       
        //Must always be called in the beginning of the program
        PTEID_ReaderSet.initSDK();

        //Enable test mode if you're using a test card
        //PTEID_Config.SetTestMode(true);

        //Gets the set of connected readers
        readerSet = PTEID_ReaderSet.instance();

        //Gets the first reader
        //When multiple readers are connected, you should iterate through the various indexes with the methods getReaderName and getReaderByName
        readerContext = readerSet.getReader();

        //Gets the Card Contact Interface and type
        if(readerContext.isCardPresent()){
            contactInterface = readerContext.getCardContactInterface();
            cardType = readerContext.getCardType();
            System.out.println("Contact Interface:" + (contactInterface == PTEID_CardContactInterface.PTEID_CARD_CONTACTLESS ? "CONTACTLESS" : "CONTACT"));
        }

        //Gets the card instance
        eidCard = readerContext.getEIDCard();

        //If the contactInterface is contactless and the card supports contactless then authenticate with PACE
        if (contactInterface == PTEID_CardContactInterface.PTEID_CARD_CONTACTLESS && cardType ==  PTEID_CardType.PTEID_CARDTYPE_IAS5){
            Scanner in = new Scanner(System.in);
            System.out.print("Insert the Card access number (CAN) for the card in use: ");
            String can_str = in.nextLine();
            eidCard.initPaceAuthentication(can_str, can_str.length(),  PTEID_CardPaceSecretType.PTEID_CARD_SECRET_CAN);
        }

        eid = eidCard.getID();
    }

    /**
     * Releases the SDK (must always be done at the end of the program)
     */
    public void release() {

        try {
            PTEID_ReaderSet.releaseSDK();

        } catch (PTEID_Exception ex) {
            System.out.println("Caught exception in some SDK method. Error: " + ex.GetMessage());
        }
    }

    /**
     * Prints the current tries left for each pin in the card
     * @throws PTEID_Exception when there is some error with the SDK methods
     */
    public void showPinInfo() throws PTEID_Exception {

        System.out.println("\nUser:");
        System.out.println("Name: " + eid.getGivenName() + " " + eid.getSurname());
        System.out.println("List of pins and respective \"tries left\":");

        //Gets the Pins objects (with the informations of all pins)
        PTEID_Pins pins = eidCard.getPins();

        //Iterates through all pins and prints the number of tries left for the user to type the correct pin 
        for (int i = 0; i < pins.count(); i++) {
            PTEID_Pin pin = pins.getPinByNumber(i);

            System.out.println(pin.getLabel() + ":\nTries left: " + pin.getTriesLeft());
        }
    }

    public void start() {

        try {
            initiate();
            showPinInfo();
        } 
        catch (PTEID_ExNoReader ex) {
            System.out.println("No reader found.");
        } 
        catch (PTEID_ExNoCardPresent ex) {
            System.out.println("No card inserted.");
        } 
        catch (PTEID_Exception ex) {
            System.out.println("Caught exception in some SDK method. Error: " + ex.GetMessage());
        }
        catch (Exception ex) {
            System.out.println("Exception caught: " + ex.getMessage());
        }
        finally {
            release();
        }
    }

    public static void main(String[] args) {
        new PinInfo().start();
    }
}
