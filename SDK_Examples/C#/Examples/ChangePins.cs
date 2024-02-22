using System;
using System.IO;
using pt.portugal.eid;

namespace Examples
{
    class ChangePins
    {
        //Main attributes needed for SDK functionalities
        PTEID_ReaderSet readerSet = null;
        PTEID_ReaderContext readerContext = null;
        PTEID_EIDCard eidCard = null;
        PTEID_EId eid = null;
        PTEID_CardType cardType = PTEID_CardType.PTEID_CARDTYPE_UNKNOWN;
        PTEID_CardContactInterface contactInterface = PTEID_CardContactInterface.PTEID_CARD_CONTACTEMPTY;



        /*
         * Initializes the SDK and sets main variables
         */
        public void Initiate()
        {
            //Must always be called in the beginning of the program
            PTEID_ReaderSet.initSDK();

            //Enable test mode if you're using a test card
            //PTEID_Config.SetTestMode(true);

            //Gets the set of connected readers, if there is any inserted
            readerSet = PTEID_ReaderSet.instance();

            //Gets the first reader
            //When multiple readers are connected, you should iterate through the various indexes with the methods getReaderName and getReaderByName
            readerContext = readerSet.getReader();

            //Gets the Card Contact Interface and type

            if (readerContext.isCardPresent())
            {
                contactInterface = readerContext.getCardContactInterface();
                cardType = readerContext.getCardType();
                Console.WriteLine("Contact Interface:" + (contactInterface == PTEID_CardContactInterface.PTEID_CARD_CONTACTLESS ? "CONTACTLESS" : "CONTACT"));
            }

            //Gets the card instance
            eidCard = readerContext.getEIDCard();

            //If the contactInterface is contactless and the card supports contactless then authenticate with PACE
            if (contactInterface == PTEID_CardContactInterface.PTEID_CARD_CONTACTLESS && cardType == PTEID_CardType.PTEID_CARDTYPE_IAS5)
            {
                Console.WriteLine("Insert the Card access number (CAN) for the card in use: ");
                string can_str = Console.ReadLine();
                uint can_size = (uint)can_str.Length;
                eidCard.initPaceAuthentication(can_str, can_size, PTEID_CardPaceSecretType.PTEID_CARD_SECRET_CAN);
            }
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
         * Changes the pin code
         */
        public void ChangePin(String pin_type)
        {

            //Gets authentication pin
            PTEID_Pins pins = eidCard.getPins();
            //The number of tries that the user has (updated with each call to verifyPin)
            uint triesLeft = uint.MaxValue;
            PTEID_Pin pin;

            //Gets the specific PIN we want
            //ADDR_PIN - Address Pin
            //AUTH_PIN - Authentication Pin
            //SIGN_PIN - Signature Pin
            if (pin_type.Equals("-addr"))
            {
                pin = pins.getPinByPinRef(PTEID_Pin.ADDR_PIN);
            }
            else if (pin_type.Equals("-auth"))
            {
                pin = pins.getPinByPinRef(PTEID_Pin.AUTH_PIN);
            }
            else if (pin_type.Equals("-sign"))
            {
                pin = pins.getPinByPinRef(PTEID_Pin.SIGN_PIN);
            }
            else
            {
                throw new Exception("Pin type doesn't exist");
            }

            //Changes the pin
            pin.changePin("", "", ref triesLeft, pin.getLabel(), false);
            Console.WriteLine("Pin changed successfully.");
        }

        public void start(string[] args)
        {
            try
            {
                Initiate();
                ChangePin(args[0]);
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
            finally
            {
                Release();
                Console.ReadLine();
            }
        }

        static void Main(string[] args)
        {
            new ChangePins().start(args);
        }
    }
}
