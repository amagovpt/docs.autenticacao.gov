using System;
using System.IO;
using pt.portugal.eid;

namespace Examples
{
    class SignXAdES
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
         * Signs files using XML Advanced Electronic Signatures
         */
        public void Sign(String[] args)
        {
            //Name of the files to be signed
            String[] files = { "../../files/input.test", "../../files/input.pdf" };

            //Output zips
            String outputXades = "../../files/Xades.zip";
            String outputXadesT = "../../files/XadesT.zip";
            String outputXadesA = "../../files/XadesA.zip";

            //Sign all files with unique signature          
            eidCard.SignXades(outputXades, files, (uint) files.Length);

            //Sign all files with unique signature including timestamp            
            eidCard.SignXadesT(outputXadesT, files, (uint) files.Length);

            //Sign all files with type A (archival) unique signature 
            eidCard.SignXadesA(outputXadesA, files, (uint) files.Length);

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
            new SignXAdES().start(args);
        }
    }
}
