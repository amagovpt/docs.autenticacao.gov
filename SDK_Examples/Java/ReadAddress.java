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
        new ReadAddress().start();
    }
}
