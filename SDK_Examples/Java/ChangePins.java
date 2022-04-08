import pt.gov.cartaodecidadao.*;


public class ChangePins {

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
     * Changes the pin code
     * @throws PTEID_Exception when there is some error with the SDK methods
     */
    public void ChangePin(String pin_type) throws PTEID_Exception {

        //Gets authentication pin
        PTEID_Pins pins = eidCard.getPins();
        PTEID_ulwrapper triesLeft = new PTEID_ulwrapper(-1);
        PTEID_Pin pin;
        boolean pin_changed = false;

        //Gets the specific PIN we want
        //ADDR_PIN - Address Pin
        //AUTH_PIN - Authentication Pin
        //SIGN_PIN - Signature Pin
        if (pin_type.equals("-addr")) {
            pin = pins.getPinByPinRef(PTEID_Pin.ADDR_PIN);
        }
        else if (pin_type.equals("-auth")) {
            pin = pins.getPinByPinRef(PTEID_Pin.AUTH_PIN);
        }
        else if (pin_type.equals("-sign")) {
            pin = pins.getPinByPinRef(PTEID_Pin.SIGN_PIN);
        }
        else {
            System.err.println("Pin type doesn't exist!");
            return;
        }

        pin_changed = pin.changePin("", "", triesLeft, pin.getLabel(), false);
        System.out.println("PIN successfully changed?: " + pin_changed);
    }

    public void start(String pin_type) {

        try {
            initiate();
            ChangePin(pin_type); 
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
        if (args.length != 1) {
            System.out.println("Usage: ChangePins [pin]");
            System.out.println("Pin:");
            System.out.println("\t-auth\t\tChange Authentication Pin.");
            System.out.println("\t-sign\t\tChange Signature Pin.");
            System.out.println("\t-addr\t\tChange Address Pin.");
        }
        else {
            new ChangePins().start(args[0]);
        }
    }
}
