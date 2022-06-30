import pt.gov.cartaodecidadao.*;

//NOTE: This example demonstrates a method - setCustomSealSize(width, height) which was introduced on version 3.7.0 of the SDK
public class SignPDFCustomSize {

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
            System.out.println("Caught exception in some SDK method. Error: " + ex.GetMessage());
        }
    }

    /**
     * Signs a pdf file
     * @param input_file The path of the document to be signed
     * @param output_file The path for the signed document
     * @throws PTEID_Exception
     */
    public void sign(String input_file, String output_file) throws PTEID_Exception {

        //To sign a document you must initialize an instance of PTEID_PDFSignature 
        //It takes the path for the input file as argument
        PTEID_PDFSignature signature = new PTEID_PDFSignature(input_file);

        //Set the signature level: BASIC/TIMESTAMP/LT/LTV - see SDK manual for details
        signature.setSignatureLevel(PTEID_SignatureLevel.PTEID_LEVEL_BASIC);

        //You can set the location and reason of signature by simply changing this strings
        String location = "Lisboa, Portugal";
        String reason = "Concordo com o conteudo do documento";

        //The page and coordinates where the signature will be printed
        int page = 1;
        double pos_x = 0.1;
        double pos_y = 0.1;

        //You can set custom dimensions for your visible seal by using this method. 
        //The first argument is the width and the second is the height of the seal.
        signature.setCustomSealSize(200, 200);
        
        //To actually sign the document you invoke this method, your authentication PIN will be requested
        //After this you can check the signed document in the path provided
        eidCard.SignPDF(signature, page, pos_x, pos_y, location, reason, output_file);
    }

    public void start(String[] args) {

        try {
            initiate();

            System.out.println("Citizen name:                " + eid.getGivenName() + " " + eid.getSurname());
            System.out.println("Card Number:                 " + eid.getDocumentNumber());
            
            sign(args[0], args[1]);
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
        if (args.length != 2) {
            System.out.println("Usage: SignPDFCustomSize [input_file] [output_file]");
        }
        else {
            new SignPDFCustomSize().start(args);
        }
    }
}
