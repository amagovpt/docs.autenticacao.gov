import java.util.logging.Level;
import java.util.logging.Logger;

import pt.gov.cartaodecidadao.*;


public class ReadAndWriteNotes {

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
            Logger.getLogger(ReadAndWriteNotes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prints notes present in the card (doesn't need any pin)
     * @throws PTEID_Exception when there is some error with the SDK methods
     */
    public void ReadNotes() throws PTEID_Exception {

        //Read current notes and print them
        String my_notes = eidCard.readPersonalNotes(); 
        System.out.println("Current notes: " + my_notes);
    
    }

    /**
     * Writes notes to the card (needs authentication pin)
     * @throws PTEID_Exception when there is some error with the SDK methods
     */
    public void WriteNotes() throws PTEID_Exception {

        //Prepares a string (notes) to be written in the card
        String notes = "We wrote successfully to the card!";
        PTEID_ByteArray personalNotes = new PTEID_ByteArray(notes.getBytes(), notes.getBytes().length);
        boolean ok;

        //Gets authentication pin
        PTEID_Pins pins = eidCard.getPins();
        PTEID_Pin pin = pins.getPinByPinRef(PTEID_Pin.AUTH_PIN);

        //Writes notes to card
        ok = eidCard.writePersonalNotes(personalNotes, pin); 
        System.out.println("Was writing successful? " + (ok ? "Yes!" : "No."));
    }

    public void start() {

        try {
            initiate();
            ReadNotes();
            WriteNotes();
            
        } catch (PTEID_Exception ex) {
            Logger.getLogger(ReadAndWriteNotes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            release();
        }
    }

    public static void main(String[] args) {
        new ReadAndWriteNotes().start();
    }
}
