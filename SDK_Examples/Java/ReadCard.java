import pt.gov.cartaodecidadao.*;
import java.util.Scanner;

public class ReadCard {

    //This static block is needed to load the SDK library
    static {
        try {
            System.loadLibrary("pteidlibj");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load. \n" + e);
            System.exit(1);
        }
    }
    
    //Main objects needed for SDK functionalities
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

        //Get the first reader
        /* When multiple readers are connected readerSet.readerCount() returns greater than 1 
		   and you should iterate through the various readers with getReaderByNum()
		   and check if there is a card present with PTEID_ReaderContext.isCardPresent() */
        readerContext = readerSet.getReader();

        //Gets the Card Contact Interface and type
        if(readerContext.isCardPresent()){
            contactInterface = readerContext.getCardContactInterface();
            cardType = readerContext.getCardType();
            System.out.println("Contact Interface:" + (contactInterface == PTEID_CardContactInterface.PTEID_CARD_CONTACTLESS ? "CONTACTLESS" : "CONTACT"));
        }

        //Get the card instance for the first reader
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
            System.out.println("Caught exception in releaseSDK(). Error: " + ex.GetMessage());
        }
    }

    /**
     * Prints Citizen Identity info present in the card
     * @throws PTEID_Exception when there is some error with the SDK methods
     */
    public void showCardInfo() throws PTEID_Exception {

        System.out.println("\n\nReading citizen identity info:");
        System.out.println("Name:                       " + eid.getGivenName() + " " + eid.getSurname());
        System.out.println("Card Type:                  " + eid.getDocumentType());
        System.out.println("Card Version:               " + eid.getDocumentVersion());
        System.out.println("Card Number:                " + eid.getDocumentNumber());
        System.out.println("Local of Request:           " + eid.getLocalofRequest());   
        System.out.println("Issuing Entity:             " + eid.getIssuingEntity());
        System.out.println("Issuing Date:               " + eid.getValidityBeginDate());
        System.out.println("Validity End Date:          " + eid.getValidityEndDate());
        System.out.println("PAN Number:                 " + eid.getDocumentPAN());
        System.out.println("Civilian ID :               " + eid.getCivilianIdNumber());
        System.out.println("Tax ID:                     " + eid.getTaxNo());
        System.out.println("Social Security ID:         " + eid.getSocialSecurityNumber());
        System.out.println("Health ID:                  " + eid.getHealthNumber());
        System.out.println("Parents:                    " + eid.getParents());
        System.out.println("Father:                     " + eid.getGivenNameFather() + " " + eid.getSurnameFather());
        System.out.println("Mother:                     " + eid.getGivenNameMother() + " " + eid.getSurnameMother());
        System.out.println("Indications:                " + eid.getAccidentalIndications());
        System.out.println("Nationality:                " + eid.getNationality());
        System.out.println("Country:                    " + eid.getCountry());
        System.out.println("Date of birth:              " + eid.getDateOfBirth());
        System.out.println("Height:                     " + eid.getHeight());
        System.out.println("Gender:                     " + eid.getGender());
        System.out.println("MRZ (Machine Readable Zone): " + eid.getMRZ1());
        System.out.println("                             " + eid.getMRZ2());
        System.out.println("                             " + eid.getMRZ3());
        System.out.println((cardType == PTEID_CardType.PTEID_CARDTYPE_IAS5 ? "CC2" : "CC1"));
    }

    public void start() {

        try {
            initiate();
            showCardInfo();
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
        new ReadCard().start();
    }
}
