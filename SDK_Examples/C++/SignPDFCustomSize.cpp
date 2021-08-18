#include "eidlib.h"
#include "eidlibException.h"
#include <iostream>

using namespace eIDMW;


//NOTE: This example has a method - setCustomSealSize(width, height) - that will only be available when the next version of the middleware is released.
int main(int argc, char **argv) {

    if (argc != 3) 
    {
        std::cout << "Usage: SignPDFCustomSize [input_file] [output_file]" << std::endl;
        return -1;
    }

    const char* input_file = argv[1];
    const char* output_file = argv[2];

    try 
    {
        //Initializes SDK (must always be called in the beginning of the program)
        PTEID_InitSDK();

        PTEID_EIDCard& eidCard = PTEID_ReaderSet::instance().getReader().getEIDCard();

        //To sign a document you must initialize an instance of PTEID_PDFSignature with the input file
        PTEID_PDFSignature signature(input_file);        

        //You can set the various signature levels by calling this method
        signature.setSignatureLevel(PTEID_SignatureLevel::PTEID_LEVEL_TIMESTAMP);

        //You can set the location and reason of signature by simply changing this strings
        const char* location = "Lisboa, Portugal";
        const char* reason = "Confirmo que li o documento e concordo com o seu conteudo, deste modo responsabilizando-me pelo mesmo";

        //The page and coordinates where the signature will be printed
        int page = 1;
        double pos_x = 0.1;
        double pos_y = 0.1;
        
        //You can set custom dimensions for your visible seal by using this method. 
        //The first argument is the width and the second is the height of the seal.
        signature.setCustomSealSize(200, 200);

        //To actually sign the document you invoke this method, your authentication PIN will be requested
        //After this you can check the signed document in the path provided
        eidCard.SignPDF(signature, page, pos_x, pos_y, location, reason, output_file);
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
    
    //Releases SDK (must always be called at the end of the program)
    PTEID_ReleaseSDK();
    return 0;
}