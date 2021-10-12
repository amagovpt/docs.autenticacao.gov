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
    
    //Main objects needed for SDK functionalities
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

        //Get the set of connected readers
        readerSet = PTEID_ReaderSet.instance();

        //Get the first reader
        /* When multiple readers are connected readerSet.readerCount() returns greater than 1 
		   and you should iterate through the various readers with getReaderByNum()
		   and check if there is a card present with PTEID_ReaderContext.isCardPresent() */
        readerContext = readerSet.getReader();

        //Get the card instance for the first reader
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
            System.out.println("Caught exception in releaseSDK(). Error: " + ex.GetMessage());
        }
    }

    /**
     * Prints address info present in the card (requires address pin)
     * @throws PTEID_Exception when there is some error with the SDK methods
     */
    public void showAddressInfo() throws PTEID_Exception {

        //The number of tries that the user has (updated with each call to verifyPin)
        PTEID_ulwrapper triesLeft = new PTEID_ulwrapper(-1);

        //Get the collection of card PINs
        PTEID_Pins pins = eidCard.getPins();
        
        //Get the specific PIN we want
        PTEID_Pin pin = pins.getPinByPinRef(PTEID_Pin.ADDR_PIN);

        //If the method verifyPin is called with "" as the first argument it prompts the middleware GUI for the user to insert its PIN
        //Otherwise we can provide the PIN as the first argument and the end result will be the same
        if (pin.verifyPin("", triesLeft, true)){

            //SDK class that handles address related information
            PTEID_Address address = eidCard.getAddr();

            System.out.println("\n\nReading address details of: " + eid.getGivenName() + " " + eid.getSurname() + ":");

			if (address.isNationalAddress()) {
				System.out.println("---National Address---");
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
				System.out.println("Door n:                         " + address.getDoorNo());
				System.out.println("Floor:                          " + address.getFloor());
				System.out.println("Side:                           " + address.getSide ());
				System.out.println("Locality:                       " + address.getLocality());
				System.out.println("Place:                          " + address.getPlace());
				System.out.println("Postal code:                    " + address.getZip4() + "-" + address.getZip3());
				System.out.println("Postal Locality:                " + address.getPostalLocality());
				System.out.println("Gen Address:                    " + address.getGeneratedAddressCode());
			}
			else {
				System.out.println("---Foreign Address---");
				System.out.println("Address:     " + address.getForeignAddress());
				System.out.println("City:        " + address.getForeignCity());
				System.out.println("Locality:    " + address.getForeignLocality());
				System.out.println("Postal Code: " + address.getForeignPostalCode());
				System.out.println("Region:      " + address.getForeignRegion());
				System.out.println("Country:     " + address.getForeignCountry());
			}
        }
        
    }

    public void start() {

        try {
            initiate();
            showAddressInfo();    
        } 
        catch (PTEID_ExNoReader ex) {
            System.out.println("No reader found.");
        } 
        catch (PTEID_ExNoCardPresent ex) {
            System.out.println("No card inserted.");
        } 
        catch (PTEID_Exception ex) {
            System.out.println("Error in address reading functions. Error detail: " + ex.GetMessage());
			/* Possible new error codes for Online address reading returned in ex.GetError()
			   These are constants defined in interface pt.gov.cartaodecidadao.pteidlibJava_WrapperConstants:
			    EIDMW_REMOTEADDR_CONNECTION_ERROR
				EIDMW_REMOTEADDR_SERVER_ERROR
				EIDMW_REMOTEADDR_CONNECTION_TIMEOUT
				EIDMW_REMOTEADDR_SMARTCARD_ERROR
				EIDMW_REMOTEADDR_UNKNOWN_ERROR
			*/
        }
        catch (Exception ex) {
            System.out.println("Exception caught: " + ex.getMessage());
        }
        finally {
            release();
        }
    }

    public static void main(String[] args) {
        new ReadAddress().start();
    }
}
