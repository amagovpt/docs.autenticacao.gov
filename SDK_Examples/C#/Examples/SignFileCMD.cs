using System;
using System.IO;
using pt.portugal.eid;

namespace Examples
{
    class SignFileCMD
    {
        /*
         * Initializes the SDK and sets main variables
         */
        public void Initiate()
        {
            //Must always be called in the beginning of the program
            PTEID_ReaderSet.initSDK();

            //You need to define the 3 values: "BASIC_AUTH_USER", "BASIC_AUTH_PASSWORD" and "BASIC_AUTH_APPID"
            //and then call this method before being able to use CMD with the SDK
            PTEID_CMDSignatureClient.setCredentials(CMDCredentials.BASIC_AUTH_USER, CMDCredentials.BASIC_AUTH_PASSWORD, CMDCredentials.BASIC_AUTH_APPID);
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

            //The page and coordinates where the signature will be printed
            int page = 1;
            double pos_x = 0.1;
            double pos_y = 0.1;

            //Instead of calling the getEIDCard() method, you can now also initialize an instance of the CMDSignatureClient to sign files with CMD
            PTEID_CMDSignatureClient client = new PTEID_CMDSignatureClient();

            //And you sign the file normally as you would in the previous versions of the SDK 
            client.SignPDF(signature, page, pos_x, pos_y, location, reason, output_file);

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
            new SignFileCMD().start(args);
        }
    }
}
