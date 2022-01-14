using System;
using System.IO;
using System.Text;
using pt.portugal.eid;

namespace Examples
{
    class ReadAndWriteNotes
    {
        //Main attributes needed for SDK functionalities
        PTEID_ReaderSet readerSet = null;
        PTEID_ReaderContext readerContext = null;
        PTEID_EIDCard eidCard = null;

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
         * Prints notes present in the card (doesn't need any pin)
         */
        public void ReadNotes()
        {

            //Read current notes and print them
            String my_notes = eidCard.readPersonalNotes(); 
            Console.WriteLine("Current notes: " + my_notes);

        }

        /*
         * Writes notes to the card (needs authentication pin)
         */
        public void WriteNotes()
        {

            //Prepares a string (notes) to be written in the card
            String notes = "We wrote successfully to the card!";

            byte[] notesBytes = Encoding.UTF8.GetBytes(notes);
            PTEID_ByteArray personalNotes = new PTEID_ByteArray(notesBytes, (uint) notesBytes.Length);
            Boolean ok;

            //Gets authentication pin
            PTEID_Pins pins = eidCard.getPins();
            PTEID_Pin pin = pins.getPinByPinRef(PTEID_Pin.AUTH_PIN);

            //Writes notes to card
            ok = eidCard.writePersonalNotes(personalNotes, pin);
            Console.WriteLine("Was writing successful? " + (ok? "Yes!" : "No."));
        }

        public void start()
        {
            try
            {
                Initiate();
                ReadNotes();
                WriteNotes();
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
            new ReadAndWriteNotes().start();
        }
    }
}
