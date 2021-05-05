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
            if (readerSet.readerCount() == 0)
            {
                throw new Exception("No Readers found!");
            }

            //Gets the first reader (index 0) and checks if there is any card inserted
            //When multiple readers are connected, you should iterate through the various indexes
            String readerName = readerSet.getReaderName(0);
            readerContext = readerSet.getReaderByName(readerName);
            if (!readerContext.isCardPresent())
            {
                throw new Exception("No card found in the reader!");
            }

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
         * @throws PTEID_Exception when there is some error with the SDK methods
         */
        public void ShowAddressInfo()
        {

            //The number of tries that the user has (updated with each call to verifyPin)
            uint triesLeft = uint.MaxValue;

            //Sets of the card PINs
            PTEID_Pins pins = eidCard.getPins();

            //Gets the specific PIN we want
            //ADDR_PIN - Address Pin
            //AUTH_PIN - Authentication Pin
            //SIGN_PIN - Signature Pin
            PTEID_Pin pin = pins.getPinByPinRef(PTEID_Pin.ADDR_PIN);

            //If the method verifyPin is called with "" as the first argument it prompts the middleware GUI for the user to insert its PIN
            //Otherwise we can send the PIN as the first argument and the end result will be the same
            if (pin.verifyPin("", ref triesLeft, true)){

                //SDK class that handles address related information
                PTEID_Address address = eidCard.getAddr();

               Console.WriteLine("\n\nReading address details of: " + eid.getGivenName() + " " + eid.getSurname() + ":");
               Console.WriteLine("Country:                        " + address.getCountryCode());
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
               Console.WriteLine("Side:                           " + address.getSide ());
               Console.WriteLine("Locality:                       " + address.getLocality());
               Console.WriteLine("Place:                          " + address.getPlace());
               Console.WriteLine("Postal code:                    " + address.getZip4() + "-" + address.getZip3());
               Console.WriteLine("Postal Locality:                " + address.getPostalLocality());
            }
        }

        public void start()
        {
            try
            {
                Initiate();
                ShowAddressInfo();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
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
