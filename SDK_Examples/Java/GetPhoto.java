import java.io.FileOutputStream;
import java.io.IOException;

import pt.gov.cartaodecidadao.*;


public class GetPhoto {

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
     * Saves the card's holder photo only in png format (ImageIO doesn't support jpeg2000 format, however you can still access to its byte format)
     * @param extension - extension of the picture (jp2 or png)
     * @param output_file - name for the photo to be stored
     * @throws PTEID_Exception when no reader or no card is found/inserted
     */
    public void savePhoto(String extension, String output_file) throws PTEID_Exception {

        //Gets the object representing the photograph present in the card
        PTEID_Photo photoObj = eid.getPhotoObj();

        //Gets the bytes of the photo in JPEG2000 format
        PTEID_ByteArray praw = photoObj.getphotoRAW(); 
        
        //Gets the bytes of the photo in PNG format
        PTEID_ByteArray ppng = photoObj.getphoto();

        System.out.println("Size of PhotoObj:       " + photoObj.getphotoImageinfo().Size());
        System.out.println("Size of JPEG2000:       " + praw.Size());
        System.out.println("Size of PNG:            " + ppng.Size());

        //Saves the PNG or JP2 format photo, with the name specified by the user
        try {
            FileOutputStream fos = new FileOutputStream(output_file);

            if (extension.equals("-png")) {
                fos.write(ppng.GetBytes());
            }
            else {
                fos.write(praw.GetBytes());
            }

            fos.close();
        } catch (IOException e) {
            System.out.println("Exception while writing image file. Message: " + e.getMessage());
        }
 
        System.out.println("Photo saved successfully!");
    }

    public void start(String[] args) {

        try {
            initiate();

            System.out.println("User:                        " + eid.getGivenName() + " " + eid.getSurname());
            System.out.println("Card Number:                 " + eid.getDocumentNumber());

            savePhoto(args[0], args[1]);
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
        if (args.length != 2 || (!args[0].equals("-png") && (!args[0].equals("-jp2")))) {
            System.out.println("Usage: GetPhoto [extension] [photo_name]");
            System.out.println("Extension:");
            System.out.println("\t-png\t\tSaves photo in png format.");
            System.out.println("\t-jp2\t\tSaves photo in JPEG2000 format.");
        }
        else {
            new GetPhoto().start(args);
        }
    }
}
