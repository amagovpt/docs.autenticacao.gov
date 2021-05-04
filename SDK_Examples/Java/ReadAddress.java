import java.util.logging.Level;
import java.util.logging.Logger;

import pt.gov.cartaodecidadao.*;


public class ReadAddress {

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
     * Initializes the SDK and sets main attributes
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
            Logger.getLogger(ReadAddress.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prints address info present in the card (requires address pin)
     * @throws PTEID_Exception when there is some error with the SDK methods
     */
    public void showAddressInfo() throws PTEID_Exception {

        //The number of tries that the user has (updated with each call to verifyPin)
        PTEID_ulwrapper triesLeft = new PTEID_ulwrapper(-1);

        //Sets of the card PINs
        PTEID_Pins pins = eidCard.getPins();
        
        //Gets the specific PIN we want
        //ADDR_PIN - Address Pin
        //AUTH_PIN - Authentication Pin
        //SIGN_PIN - Signature Pin
        PTEID_Pin pin = pins.getPinByPinRef(PTEID_Pin.ADDR_PIN);

        //If the method verifyPin is called with "" as the first argument it prompts the middleware GUI for the user to insert its PIN
        //Otherwise we can send the PIN as the first argument and the end result will be the same
        if (pin.verifyPin("", triesLeft, true)){

            //SDK class that handles address related information
            PTEID_Address address = eidCard.getAddr();

            System.out.println("\n\nReading address details of: " + eid.getGivenName() + " " + eid.getSurname() + ":");
            System.out.println("Country:                        " + address.getCountryCode());
            System.out.println("District:                       " + address.getDistrict());
            System.out.println("District (code):                " + address.getDistrictCode());
            System.out.println("Municipality:                   " + address.getMunicipality());
            System.out.println("Municipality (code):            " + address.getMunicipalityCode());   
            System.out.println("Parish:                         " + address.getCivilParish());
            System.out.println("Parish (code):                  " + address.getCivilParishCode());
            System.out.println("Street Type (Abbreviated):      " + address.getAbbrStreetType());
            System.out.println("Street Type:                    " + address.getStreetType());
            System.out.println("Street Name:                    " + address.getStreetName());
            System.out.println("Building Type (Abbreviated):    " + address.getAbbrBuildingType());
            System.out.println("Building Type:                  " + address.getBuildingType());
            System.out.println("Door nº:                        " + address.getDoorNo());
            System.out.println("Floor:                          " + address.getFloor());
            System.out.println("Side:                           " + address.getSide ());
            System.out.println("Locality:                       " + address.getLocality());
            System.out.println("Place:                          " + address.getPlace());
            System.out.println("Postal code:                    " + address.getZip4() + "-" + address.getZip3());
            System.out.println("Postal Locality:                " + address.getPostalLocality());
        }
        
    }

    public void start() {

        try {

            initiate();
            showAddressInfo();
            
        } catch (PTEID_Exception ex) {
            Logger.getLogger(ReadAddress.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            release();
        }
    }

    public static void main(String[] args) {
        new ReadAddress().start();
    }
}
