using System;
using System.IO;
using pt.portugal.eid;

//NOTE: This example has a method - setCustomSealSize(width, height) - that will only be available when the next version of the middleware is released.
namespace Examples
{
    class SignFileCustom
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
         * Signs a pdf file
         */
        public void Sign(String input_file, String output_file)
        {

            //To sign a document you must initialize an instance of PTEID_PDFSignature 
            //It may take the path for the input file as argument
            PTEID_PDFSignature signature = new PTEID_PDFSignature(input_file);

            //You can set the location and reason fields of the signature
            String location = "Lisboa, Portugal";
            String reason = "Concordo com o conteudo do documento";

            //The page number where the signature will be printed
            int page = 1;

            //The location in the page where the visible signature will be printed in percentage of page height/width
            double pos_x = 0.1;
            double pos_y = 0.1;

            //You can set custom dimensions for your visible seal by using this method. 
            //The first argument is the width and the second is the height of the seal.
            signature.setCustomSealSize(200, 200);

            //To actually sign the document you invoke this method, and the signature PIN will be requested in an SDK dialog
            eidCard.SignPDF(signature, page, pos_x, pos_y, location, reason, output_file);

            Console.WriteLine("File signed with success.");
        }

        public void start(string[] args)
        {
            try
            {
                Initiate();
                Sign(args[0], args[1]);
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
            finally
            {
                Release();
                Console.ReadLine();
            }
        }

        static void Main(string[] args)
        {
            new SignFile().start(args);
        }
    }
}
