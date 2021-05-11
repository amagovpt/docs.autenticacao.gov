import java.util.logging.Level;
import java.util.logging.Logger;
import pt.gov.cartaodecidadao.*;


public class SignXAdES {

    //This static block is needed to load the sdk library
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
            Logger.getLogger(ReadCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Signs files with XML Advanced Electronic Signatures
     * @throws PTEID_Exception
     */
    public void sign() throws PTEID_Exception {

        //Name of the files to be signed
        String[] files = {"files/input.test", "files/input.pdf"};

        //Output zips
        String outputXades = "files/Xades.zip";
        String outputXadesT = "files/XadesT.zip";
        String outputXadesA = "files/XadesA.zip";

        //Sign all files with unique signature          
        eidCard.SignXades(outputXades, files, files.length);

        //Sign all files with unique signature including timestamp            
        eidCard.SignXadesT(outputXadesT, files, files.length);
      
        //Sign all files with type A (archival) unique signature 
        eidCard.SignXadesA(outputXadesA, files, files.length);
    }

    public void start() {

        try {
            initiate();

            System.out.println("User:                        " + eid.getGivenName() + " " + eid.getSurname());
            System.out.println("Card Number:                 " + eid.getDocumentNumber());
            
            sign();
        
        } catch (PTEID_Exception ex) {
            Logger.getLogger(ReadCard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            release();
        }
    }

    public static void main(String[] args) {
        new SignXAdES().start();
    }
}
