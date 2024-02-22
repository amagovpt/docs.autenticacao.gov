using System;
using pt.portugal.eid;

namespace Examples
{
    //NOTE: This example demonstrates a method - SignASiC() which was introduced on version 3.8.0 of the SDK
    class SignXAdES
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
         * Signs files using XML Advanced Electronic Signatures
         */
        public void Sign(String[] args)
        {
            //Paths of the files to be signed
            String[] files = args;

            //Output zips
            String outputXades = "Xades-B.asice";
            String outputXadesT = "Xades-T.asice";
            String outputXadesA = "Xades-A.asice";

            //Sign all files with unique signature          
            eidCard.SignXades(outputXades, files, (uint) files.Length, PTEID_SignatureLevel.PTEID_LEVEL_BASIC);

            //Sign all files with unique signature including timestamp            
            eidCard.SignXades(outputXadesT, files, (uint) files.Length, PTEID_SignatureLevel.PTEID_LEVEL_TIMESTAMP);

            //Sign all files with type A (archival) unique signature 
            eidCard.SignXades(outputXadesA, files, (uint) files.Length, PTEID_SignatureLevel.PTEID_LEVEL_LTV);
			
			Console.WriteLine("Add a second XAdES-B signature to 'Xades-B.asice'");
			eidCard.SignASiC(outputXades, PTEID_SignatureLevel.PTEID_LEVEL_BASIC);

            Console.WriteLine("Files signed with success.");
        }


        public void start(string[] args)
        {
            try
            {
                Initiate();
                Sign(args);
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
			if (args.Length == 0) {
				Console.WriteLine("Missing arguments for signature input files!");
				return;
			}
            new SignXAdES().start(args);
        }
    }
}
