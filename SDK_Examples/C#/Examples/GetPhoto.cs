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
