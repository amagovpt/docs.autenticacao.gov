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

        System.out.println("Performing XAdES-B signature (2 files)");

        //Sign all files with unique signature          
        eidCard.SignXades(outputXades, files, files.length);

        System.out.println("Performing XAdES-T signature (2 files)");

        //Sign all files with unique signature including timestamp            
        eidCard.SignXadesT(outputXadesT, files, files.length);

        System.out.println("Performing XAdES-LTA signature (2 files)");
      
        //Sign all files with type A (archival) unique signature 
        eidCard.SignXadesA(outputXadesA, files, files.length);
    }

    public void start() {

        try {
            initiate();

            System.out.println("User:                        " + eid.getGivenName() + " " + eid.getSurname());
            System.out.println("Card Number:                 " + eid.getDocumentNumber());
            
            sign();
        
        } catch (PTEID_ExNoReader ex) {
            System.out.println("No reader found.");
        } catch (PTEID_ExNoCardPresent ex) {
            System.out.println("No card inserted.");
        } catch (PTEID_Exception ex) {
            Logger.getLogger(ReadCard.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            release();
        }
    }

    public static void main(String[] args) {
        new SignXAdES().start();
    }
}
