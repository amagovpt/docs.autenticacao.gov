#include "eidlib.h"
#include "eidlibException.h"

#include "CMDCredentials.h"

#include <iostream>

using namespace eIDMW;

int main(int argc, char **argv) {

    const char* input_file = "files/input.pdf";
    const char* output_file = "files/output.pdf";
    
    try 
    {
        //Initialize SDK (must always be called in the beginning of the program)
        PTEID_InitSDK();

        //You need to call this static method before using any other method on class PTEID_CMDSignatureClient
        PTEID_CMDSignatureClient::setCredentials(BASIC_AUTH_USER, BASIC_AUTH_PASSWORD, BASIC_AUTH_APPID);

        //To sign a document you must initialize an instance of PTEID_PDFSignature
        PTEID_PDFSignature signature(input_file);

        //You can set the various signature levels by calling this method
        signature.setSignatureLevel(PTEID_SignatureLevel::PTEID_LEVEL_TIMESTAMP);

        //You can set the location and reason of signature by simply changing this strings
        const char * location = "Lisboa, Portugal";
        const char * reason = "Concordo com o conteudo do documento";
        
        //The page and coordinates where the signature will be printed
        int page = 1;
        double pos_x = 0.1;
        double pos_y = 0.1;

        //Instead of calling the getEIDCard() method, you can now also initialize an instance of the CMDSignatureClient to sign files with CMD
        PTEID_CMDSignatureClient client;

        //And you sign the file normally as you would in the previous versions of the SDK 
        client.SignPDF(signature, page, pos_x, pos_y, location, reason, output_file);    

        std::cout << "File signed successfully" << std::endl;

    }
    catch (PTEID_Exception &e) 
    {
        std::cerr << "Caught exception in some SDK method. Error: " << e.GetMessage() << std::endl;
    }
    
    PTEID_ReleaseSDK();
    return 0;
}