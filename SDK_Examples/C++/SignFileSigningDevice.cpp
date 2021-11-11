#include "eidlib.h"
#include "eidlibException.h"

#include "CMDCredentials.h"

#include <iostream>

using namespace eIDMW;

int main(int argc, char **argv) {

    
    if (argc != 2) 
    {
        std::cout << "Incorrect usage. Should be:\n./CMD_SignFile [-CMD|-CARD|-BOTH]" << std::endl;
        return -1;
    }

    const char* input_file = "files/input.pdf";
    const char* output_file = "files/output.pdf";
    std::string flag = argv[1];

    try 
    {
        //Initializes SDK (must always be called in the beginning of the program)
        PTEID_InitSDK();

        //You need to call this method before being able to use CMD with the SDK
        PTEID_CMDSignatureClient::setCredentials(BASIC_AUTH_USER, BASIC_AUTH_PASSWORD, BASIC_AUTH_APPID);

        //To sign a document you must initialize an instance of PTEID_PDFSignature
        PTEID_PDFSignature signature(input_file);

        //You can set the various signature levels by calling this method
        signature.setSignatureLevel(PTEID_SignatureLevel::PTEID_LEVEL_TIMESTAMP);

        //You can set the location and reason fields of the signature
        const char * location = "Lisboa, Portugal";
        const char * reason = "Concordo com o conteudo do documento";
        
        //The page and coordinates where the signature will be printed
        int page = 1;
        double pos_x = 0.1;
        double pos_y = 0.1;

        //Instead of calling the getEIDCard() method, you can now call PTEID_SigningDeviceFactory::getSigningDevice()
        //This way you can choose to use CMD or your card to sign the files

        //Get SigningDeviceFactory instance
        PTEID_SigningDeviceFactory &factory = PTEID_SigningDeviceFactory::instance();

        if (!flag.compare("-CMD")) 
        {
            //If you only want CMD signature, you can disallow card signature by setting the first argument to false
            PTEID_SigningDevice& device = factory.getSigningDevice(false, true);

            //And you sign the file normally as you would in the previous versions of the SDK 
            device.SignPDF(signature, page, pos_x, pos_y, location, reason, output_file);    

            std::cout << "File signed successfully" << std::endl;
        }
        else if (!flag.compare("-CARD")) 
        {
            //If you only want card signature, you can disallow CMD signature by setting the second argument to false
            PTEID_SigningDevice& device = factory.getSigningDevice(true, false);

            //And you sign the file normally as you would in the previous versions of the SDK 
            device.SignPDF(signature, page, pos_x, pos_y, location, reason, output_file);    

            std::cout << "File signed successfully" << std::endl;
        } 
        else if (!flag.compare("-BOTH")) 
        {
            //If you want both methods to be available (CMD and CARD signature) you can set both arguments to true(default)
            PTEID_SigningDevice& device = factory.getSigningDevice();

            //And you sign the file normally as you would in the previous versions of the SDK 
            device.SignPDF(signature, page, pos_x, pos_y, location, reason, output_file);    

            std::cout << "File signed successfully" << std::endl;
        }
        else {
            std::cout << "Flag not supported, please provided a valid flag: [-CMD|-CARD|-BOTH]" << std::endl;            
        }

    }
    catch (PTEID_ExNoReader &e) 
    {
        std::cout << "No readers found!" << std::endl;
    }
    catch (PTEID_ExNoCardPresent &e) 
    {
        std::cout << "No card found in the reader!" << std::endl;
    }
    catch (PTEID_Exception &e) 
    {
        std::cout << "Caught exception in some SDK method. Error: " << e.GetMessage() << std::endl;
    }
    
    PTEID_ReleaseSDK();
    return 0;
}