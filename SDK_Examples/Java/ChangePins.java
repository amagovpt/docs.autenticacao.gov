import java.util.logging.Level;
import java.util.logging.Logger;

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
     * @throws Exception when no reader or no card is found/inserted
     */
    public void initiate() throws PTEID_Exception, Exception {
       
        //Must always be called in the beginning of the program
        PTEID_ReaderSet.initSDK();

        //Gets the set of connected readers, if there is any inserted
        readerSet = PTEID_ReaderSet.instance();
        if (readerSet.readerCount() == 0) {
            throw new Exception("No Readers found!");
        }

        //Gets the first reader (index 0) and checks if there is any card inserted
        //When multiple readers are connected, you should iterate through the various indexes
        String readerName = readerSet.getReaderName(0);
        readerContext = readerSet.getReaderByName(readerName);
        if (!readerContext.isCardPresent()) {
            throw new Exception("No card found in the reader!");
        }

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
            Logger.getLogger(ChangePins.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Changes the pin code
     * @throws PTEID_Exception when there is some error with the SDK methods
     * @throws Exception when the user didn't specify a valid type of pin
     */
    public void ChangePin(String pin_type) throws PTEID_Exception, Exception {

        //Gets authentication pin
        PTEID_Pins pins = eidCard.getPins();
        PTEID_ulwrapper triesLeft = new PTEID_ulwrapper(-1);
        PTEID_Pin pin;

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
            throw new Exception("Pin type doesn't exist");
        }

        //Changes the pin
        pin.changePin("", "", triesLeft, pin.getLabel(), false);
    }

    public void start(String pin_type) {

        try {
            
            initiate();
            ChangePin(pin_type);
            
        } catch (PTEID_Exception ex) {
            Logger.getLogger(ChangePins.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            release();
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Incorrect usage. Should pass 1 argument (type of pin you want to modify).");
        }
        else {
            new ChangePins().start(args[0]);
        }
    }
}
