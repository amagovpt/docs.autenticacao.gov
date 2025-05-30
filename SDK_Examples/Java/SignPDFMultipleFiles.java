import pt.gov.cartaodecidadao.*;

import java.util.Scanner;

public class SignPDFMultipleFiles {

    //This static block is needed to load the sdk library
    static {
        try {
            System.loadLibrary("pteidlibj");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load. \n" + e);
            System.exit(1);
        }
    }
    
    PTEID_ReaderSet readerSet = null;
    PTEID_ReaderContext readerContext = null;
    PTEID_EIDCard eidCard = null;
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

        //Gets the first reader
        //When multiple readers are connected, you can iterate through the various reader objects with the methods getReaderName and getReaderByName or getReaderByNum
        //Any reader can be checked for an inserted card with PTEID_ReaderContext.isCardPresent()
        readerContext = readerSet.getReader();

        //Gets the Card Contact Interface and type
        if(readerContext.isCardPresent()){
            contactInterface = readerContext.getCardContactInterface();
            cardType = readerContext.getCardType();
            System.out.println("Contact Interface:" + (contactInterface == PTEID_CardContactInterface.PTEID_CARD_CONTACTLESS ? "CONTACTLESS" : "CONTACT"));
        }

        //Gets the card instance
        eidCard = readerContext.getEIDCard();

        //If the contactInterface is contactless and the card supports contactless then authenticate with PACE
        if (contactInterface == PTEID_CardContactInterface.PTEID_CARD_CONTACTLESS && cardType ==  PTEID_CardType.PTEID_CARDTYPE_IAS5){
            Scanner in = new Scanner(System.in);
            System.out.print("Insert the Card access number (CAN) for the card in use: ");
            String can_str = in.nextLine();
            eidCard.initPaceAuthentication(can_str, can_str.length(),  PTEID_CardPaceSecretType.PTEID_CARD_SECRET_CAN);
        }
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
     * Signs multiple pdf files
     * @param args An array starting with the output directory and followed by various pdf files to sign
     * @throws PTEID_Exception
     */
    public void sign(String[] args) throws PTEID_Exception {

        //Get the output directory
        String output_directory = args[0];

        //To sign a document you must initialize an instance of PTEID_PDFSignature 
        PTEID_PDFSignature signature = new PTEID_PDFSignature();

        //Iterate through the various files to sign and add them to the batch to be signed
        for (int i = 1; i < args.length; i++) 
        {
            signature.addToBatchSigning(args[i]);
        }

        //You can set the location and reason of signature by simply changing this strings
        String location = "Lisboa, Portugal";
        String reason = "Concordo com o conteudo do documento";

        //The page and coordinates where the signature will be printed
        int page = 1;
        double pos_x = 0.1;
        double pos_y = 0.1;
        
        //To actually sign the document you invoke this method, your authentication PIN will be requested
        //After this you can check the signed document in the path provided
        eidCard.SignPDF(signature, page, pos_x, pos_y, location, reason, output_directory);
    }

    public void start(String[] args) {

        try {
            initiate();
            
            sign(args);
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
        if (args.length < 2) {
            System.out.println("Usage: SignPDFMultipleFiles [output_directory] [filename_1] [filename_2] ... [filename_n]");
        }
        else {
            new SignPDFMultipleFiles().start(args);
        }
    }
}
