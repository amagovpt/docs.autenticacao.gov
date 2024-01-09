using System;
using System.IO;
using System.Drawing;
using pt.portugal.eid;

namespace Examples
{
    class GetPhoto
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

            //Sets test mode to true so that CC2 can be tested
            PTEID_Config.SetTestMode(true);

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
                Console.WriteLine("Insert the CAN for this EIDCard: ");
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
         * Saves the card's holder photo in PNG format
         */
        public void SavePhoto(String output_file)
        {
            //Gets the object representing the photograph present in the card
            PTEID_Photo photoObj = eid.getPhotoObj();

            //Gets the bytes of the photo in JPEG2000 format
            PTEID_ByteArray praw = photoObj.getphotoRAW();

            //Gets the bytes of the photo in PNG format
            PTEID_ByteArray ppng = photoObj.getphoto();

            Console.WriteLine("Size of PhotoObj:       " + photoObj.getphotoImageinfo().Size());
            Console.WriteLine("Size of JPEG2000:       " + praw.Size());
            Console.WriteLine("Size of PNG:            " + ppng.Size());

            //Saves the PNG format photo, with the name specified by the user
            MemoryStream memoryStream = new MemoryStream(ppng.GetBytes());
            Image img = Image.FromStream(memoryStream);
            img.Save("../../files/" + output_file);

            Console.WriteLine("Image was successfuly saved at target location.");
        }

        public void start(string[] args)
        {
            try
            {
                Initiate();
                SavePhoto(args[0]);
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
            finally {
                Release();
                Console.ReadLine();
            }
        }   
    
        static void Main(string[] args)
        {
            new GetPhoto().start(args);
        }
    }
}
