import pt.gov.cartaodecidadao.*;


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


    /**
     * Initializes the SDK and sets main variables
     * @throws PTEID_Exception when there is some error with the SDK methods
     */
    public void initiate() throws PTEID_Exception {
       
        //Must always be called in the beginning of the program
        PTEID_ReaderSet.initSDK();

        //Gets the set of connected readers
        readerSet = PTEID_ReaderSet.instance();

        //Gets the first reader
        //When multiple readers are connected, you should iterate through the various indexes with the methods getReaderName and getReaderByName
        readerContext = readerSet.getReader();

        //Gets the card instance
        eidCard = readerContext.getEIDCard();
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
