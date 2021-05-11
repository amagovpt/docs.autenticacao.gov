import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

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
            Logger.getLogger(ReadCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Saves the card's holder photo only in png format (ImageIO doesn't support jpeg2000 format, however you can still access to its byte format)
     * @param output_file - name for the photo to be stored
     * @throws PTEID_Exception when no reader or no card is found/inserted
     */
    public void savePhoto(String output_file) throws PTEID_Exception {

        //Gets the object representing the photograph present in the card
        PTEID_Photo photoObj = eid.getPhotoObj();

        //Gets the bytes of the photo in JPEG2000 format
        PTEID_ByteArray praw = photoObj.getphotoRAW(); 
        
        //Gets the bytes of the photo in PNG format
        PTEID_ByteArray ppng = photoObj.getphoto();

        System.out.println("Size of PhotoObj:       " + photoObj.getphotoImageinfo().Size());
        System.out.println("Size of JPEG2000:       " + praw.Size());
        System.out.println("Size of PNG:            " + ppng.Size());

        //Saves the PNG format photo, with the name specified by the user
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(ppng.GetBytes());
            BufferedImage bImage = ImageIO.read(bis);
            ImageIO.write(bImage, "png", new File(output_file));
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }

    public void start(String[] args) {

        try {
            initiate();

            System.out.println("User:                        " + eid.getGivenName() + " " + eid.getSurname());
            System.out.println("Card Number:                 " + eid.getDocumentNumber());

            savePhoto(args[0]);
        
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
        if (args.length != 1) {
            System.out.println("Incorrect usage. Should pass 1 argument: the name for the saved photo.");
        }
        else {
            new GetPhoto().start(args);
        }
    }
}
