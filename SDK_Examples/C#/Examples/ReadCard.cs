using System;
using pt.portugal.eid;

namespace Examples
{
    class ReadCard
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
            if (contactInterface == PTEID_CardContactInterface.PTEID_CARD_CONTACTLESS && cardType ==  PTEID_CardType.PTEID_CARDTYPE_IAS5){
                Console.WriteLine("Insert the CAN for this EIDCard: ");
                string can_str = Console.ReadLine();
                uint can_size = (uint) can_str.Length;
                eidCard.initPaceAuthentication(can_str, can_size,  PTEID_CardPaceSecretType.PTEID_CARD_SECRET_CAN);
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
         * Prints main info present in the card (that doesn't need address pin)
         */
        public void ShowCardInfo()
        {
            Console.WriteLine("\n\nReading card details:");
            Console.WriteLine("Name:                       " + eid.getGivenName() + " " + eid.getSurname());
            Console.WriteLine("Card Type:                  " + eid.getDocumentType());
            Console.WriteLine("Card Version:               " + eid.getDocumentVersion());
            Console.WriteLine("Card Number:                " + eid.getDocumentNumber());
            Console.WriteLine("Local of Request:           " + eid.getLocalofRequest());
            Console.WriteLine("Issuing Entity:             " + eid.getIssuingEntity());
            Console.WriteLine("Issuing Date:               " + eid.getValidityBeginDate());
            Console.WriteLine("Validity End Date:          " + eid.getValidityEndDate());
            Console.WriteLine("PAN Number:                 " + eid.getDocumentPAN());
            Console.WriteLine("Civilian ID :               " + eid.getCivilianIdNumber());
            Console.WriteLine("Tax ID:                     " + eid.getTaxNo());
            Console.WriteLine("Social Security ID:         " + eid.getSocialSecurityNumber());
            Console.WriteLine("Health ID:                  " + eid.getHealthNumber());
            Console.WriteLine("Parents:                    " + eid.getParents());
            Console.WriteLine("Father:                     " + eid.getGivenNameFather() + " " + eid.getSurnameFather());
            Console.WriteLine("Mother:                     " + eid.getGivenNameMother() + " " + eid.getSurnameMother());
            Console.WriteLine("Indications:                " + eid.getAccidentalIndications());
            Console.WriteLine("Nationality:                " + eid.getNationality());
            Console.WriteLine("Country:                    " + eid.getCountry());
            Console.WriteLine("Date of birth:              " + eid.getDateOfBirth());
            Console.WriteLine("Height:                     " + eid.getHeight());
            Console.WriteLine("Gender:                     " + eid.getGender());
            Console.WriteLine("MRZ (Machine Readable Zone): " + eid.getMRZ1());
            Console.WriteLine("                             " + eid.getMRZ2());
            Console.WriteLine("                             " + eid.getMRZ3());
            Console.WriteLine((cardType == PTEID_CardType.PTEID_CARDTYPE_IAS5 ? "CC2" : "CC1"));
        }

        public void start()
        {
            try
            {
                Initiate();
                ShowCardInfo();
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
            new ReadCard().start();
        }
    }
}
