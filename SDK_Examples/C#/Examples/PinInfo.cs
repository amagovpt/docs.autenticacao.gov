using System;
using pt.portugal.eid;

namespace Examples
{
    class PinInfo
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
         * Prints the current tries left for each pin in the card
         */
        public void showPinInfo()
        {

            Console.WriteLine("User:");
            Console.WriteLine("Name: " + eid.getGivenName() + " " + eid.getSurname());
            Console.WriteLine("List of pins and respective \"tries left\":");

            //Gets the Pins objects (with the informations of all pins)
            PTEID_Pins pins = eidCard.getPins();

            //Iterates through all pins and prints the number of tries left for the user to type the correct pin 
            for (uint i = 0; i < pins.count(); i++) {
                PTEID_Pin pin = pins.getPinByNumber(i);

                Console.WriteLine(pin.getLabel() + ":\nTries left: " + pin.getTriesLeft());
            }
        }

        public void start()
        {
            try
            {
                Initiate();
                showPinInfo();
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
                Console.WriteLine(ex.GetMessage());
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
            finally {
                Release();
                Console.ReadLine();
            }
        }   
    
        static void Main(string[] args)
        {
            new PinInfo().start();
        }
    }
}
