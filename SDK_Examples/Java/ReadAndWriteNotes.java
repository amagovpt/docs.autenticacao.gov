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
        new ReadAndWriteNotes().start();
    }
}
