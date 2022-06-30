using System;
using pt.portugal.eid;

namespace Examples
{
    class ReadAddress
    {
        //Main attributes needed for SDK functionalities
        PTEID_ReaderSet readerSet = null;
        PTEID_ReaderContext readerContext = null;
        PTEID_EIDCard eidCard = null;
        PTEID_EId eid = null;


        /*
         * Initializes the SDK and sets main variables
         */
        public void Initiate()
        {
            //Must always be called in the beginning of the program
            PTEID_ReaderSet.initSDK();

            //Gets the set of connected readers, if there is any inserted
            readerSet = PTEID_ReaderSet.instance();

            //Gets the first reader
            //When multiple readers are connected, you should iterate through the various indexes with the methods getReaderName and getReaderByName
            readerContext = readerSet.getReader();

            //Gets the card instance
            eidCard = readerContext.getEIDCard();
            eid = eidCard.getID();
        }

        /*
         * Releases the SDK (must always be done at the end of the program)
         */
        public void Release()
        {
            try
            {
                PTEID_ReaderSet.releaseSDK();
            }
            catch (PTEID_Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
        }

        /*
         * Prints address info present in the card (requires address pin)
         */
        public void ShowAddressInfo()
        {
            //The number of tries that the user has (updated with each call to verifyPin)
            uint triesLeft = uint.MaxValue;

            //Get the collection of card PINs
            PTEID_Pins pins = eidCard.getPins();

            //Get the specific PIN we want
            PTEID_Pin pin = pins.getPinByPinRef(PTEID_Pin.ADDR_PIN);

            //If the method verifyPin is called with "" as the first argument it prompts the middleware GUI for the user to insert its PIN
            //Otherwise we can provide the PIN as the first argument and the end result will be the same
            if (pin.verifyPin("", ref triesLeft, true))
            {

                //SDK class that handles address related information
                PTEID_Address address = eidCard.getAddr();
                Console.WriteLine("\n\nReading address details of: " + eid.getGivenName() + " " + eid.getSurname() + ":");

                if (address.isNationalAddress())
                {
                    Console.WriteLine("---National Address---");
                    Console.WriteLine("District:                       " + address.getDistrict());
                    Console.WriteLine("District (code):                " + address.getDistrictCode());
                    Console.WriteLine("Municipality:                   " + address.getMunicipality());
                    Console.WriteLine("Municipality (code):            " + address.getMunicipalityCode());
                    Console.WriteLine("Parish:                         " + address.getCivilParish());
                    Console.WriteLine("Parish (code):                  " + address.getCivilParishCode());
                    Console.WriteLine("Street Type (Abbreviated):      " + address.getAbbrStreetType());
                    Console.WriteLine("Street Type:                    " + address.getStreetType());
                    Console.WriteLine("Street Name:                    " + address.getStreetName());
                    Console.WriteLine("Building Type (Abbreviated):    " + address.getAbbrBuildingType());
                    Console.WriteLine("Building Type:                  " + address.getBuildingType());
                    Console.WriteLine("Door nº:                        " + address.getDoorNo());
                    Console.WriteLine("Floor:                          " + address.getFloor());
                    Console.WriteLine("Side:                           " + address.getSide());
                    Console.WriteLine("Locality:                       " + address.getLocality());
                    Console.WriteLine("Place:                          " + address.getPlace());
                    Console.WriteLine("Postal code:                    " + address.getZip4() + "-" + address.getZip3());
                    Console.WriteLine("Postal Locality:                " + address.getPostalLocality());
                }
                else
                {
                    Console.WriteLine("---Foreign Address---");
                    Console.WriteLine("Address:     " + address.getForeignAddress());
                    Console.WriteLine("City:        " + address.getForeignCity());
                    Console.WriteLine("Locality:    " + address.getForeignLocality());
                    Console.WriteLine("Postal Code: " + address.getForeignPostalCode());
                    Console.WriteLine("Region:      " + address.getForeignRegion());
                    Console.WriteLine("Country:     " + address.getForeignCountry());
                }
            }
        }

        public void start()
        {
            try
            {
                Initiate();
                ShowAddressInfo();
            }
            catch (PTEID_ExNoReader)
            {
                Console.WriteLine("No reader found.");
            }
            catch (PTEID_ExNoCardPresent)
            {
                Console.WriteLine("No card inserted.");
            }
            catch (PTEID_Exception ex)
            {
                Console.WriteLine("Error in address reading functions. Detail: " + ex.GetMessage());

                /* Possible new error codes for Online address reading returned in ex.GetError()
                   These are constants defined in class pteidlib_dotNet:
                   EIDMW_REMOTEADDR_CONNECTION_ERROR
                   EIDMW_REMOTEADDR_SERVER_ERROR
                   EIDMW_REMOTEADDR_CONNECTION_TIMEOUT
                   EIDMW_REMOTEADDR_SMARTCARD_ERROR
                   EIDMW_REMOTEADDR_UNKNOWN_ERROR
                */
            }
            finally
            {
                Release();
                Console.ReadLine();
            }
        }

        static void Main(string[] args)
        {
            new ReadAddress().start();
        }
    }
}
