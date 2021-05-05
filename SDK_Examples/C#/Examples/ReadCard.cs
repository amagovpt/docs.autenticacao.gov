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
         * Prints main info present in the card (that doesn't need address pin)
         */
        public void ShowCardInfo()
        {
            Console.WriteLine("\n\nReading card details:");
            Console.WriteLine("Name:                       " + eid.getGivenName() + " " + eid.getSurname());
            Console.WriteLine("Card Type:                  " + eid.getDocumentType());
            Console.WriteLine("Card Version:               " + eid.getDocumentVersion());
            Console.WriteLine("Validaty Status:            " + eid.getValidation());
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
            Console.WriteLine("MRZ:                        " + eid.getMRZ1());
            Console.WriteLine("                            " + eid.getMRZ2());
            Console.WriteLine("                            " + eid.getMRZ3());
        }

        public void start()
        {
            try
            {
                Initiate();
                ShowCardInfo();
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
            new ReadCard().start();
        }
    }
}
