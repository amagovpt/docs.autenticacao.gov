import java.util.logging.Level;
import java.util.logging.Logger;
import pt.gov.cartaodecidadao.*;

public class SignFileCMD {

    //This static block is needed to load the sdk library
    static {
        try {
            System.loadLibrary("pteidlibj");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load. \n" + e);
            System.exit(1);
        }
    }

    /**
     * Initializes the SDK and sets main variables
     * @throws PTEID_Exception when there is some error with the SDK methods
     */
    public void initiate() throws PTEID_Exception {
       
        //Must always be called in the beginning of the program
        PTEID_ReaderSet.initSDK();

        //You need to define the 3 values: "BASIC_AUTH_USER", "BASIC_AUTH_PASSWORD" and "BASIC_AUTH_APPID"
        //and then call this method before being able to use CMD with the SDK
        PTEID_CMDSignatureClient.setCredentials(CMDCredentials.BASIC_AUTH_USER, CMDCredentials.BASIC_AUTH_PASSWORD, CMDCredentials.BASIC_AUTH_APPID);
    }

    /**
     * Releases the SDK (must always be done at the end of the program)
     */
    public void release() {

        try {
            PTEID_ReaderSet.releaseSDK();
        } catch (PTEID_Exception ex) {
            Logger.getLogger(SignFileCMD.class.getName()).log(Level.SEVERE, null, ex);
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
        //It may take the path for the input file as an argument
        PTEID_PDFSignature signature = new PTEID_PDFSignature(input_file);

        //You can set the location and reason fields of the signature
        String location = "Lisboa, Portugal";
        String reason = "Concordo com o conteudo do documento";

        //The page and coordinates where the signature will be printed
        int page = 1;
        double pos_x = 0.1;
        double pos_y = 0.1;
        
        //Instead of calling the getEIDCard() method, you can now also initialize an instance of the CMDSignatureClient to sign files with CMD
        PTEID_CMDSignatureClient client = new PTEID_CMDSignatureClient();

        //And you sign the file normally as you would in the previous versions of the SDK 
        client.SignPDF(signature, page, pos_x, pos_y, location, reason, output_file);

        System.out.println("File signed succesfully");
    }

    public void start(String[] args) {

        try {

            initiate();
            sign(args[0], args[1]);
        
        } catch (PTEID_ExNoReader ex) {
            System.out.println("No reader found.");
        } catch (PTEID_ExNoCardPresent ex) {
            System.out.println("No card inserted.");
        } catch (PTEID_Exception ex) {
            Logger.getLogger(SignFileCMD.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            release();
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Incorrect usage. Should pass 2 arguments.");
            System.out.println("The first is the file to sign and the second is the name for the signed document.");
        }
        else {
            new SignFileCMD().start(args);
        }
    }
}
