#include "eidlib.h"
#include "eidlibException.h"
#include <iostream>

using namespace eIDMW;

int main(int argc, char **argv) {

    if (argc < 3) 
    {
        std::cout << "Usage: SignPDFMultipleFiles [output_directory] [filename_1] [filename_2] ... [filename_n]" << std::endl;
        return -1;
    }

    try 
    {
        //Initializes SDK (must always be called in the beginning of the program)
        PTEID_InitSDK();

        PTEID_EIDCard& eidCard = PTEID_ReaderSet::instance().getReader().getEIDCard();

        //Get the output directory
        const char* output_directory = argv[1];

        //To sign a document you must initialize an instance of PTEID_PDFSignature 
        PTEID_PDFSignature signature; 

        //signature.setSignatureLevel(PTEID_SignatureLevel::PTEID_LEVEL_TIMESTAMP);

        //Iterate through the various files to sign and add them to the batch to be signed
        for (int i = 2; i < argc; i++) 
        {
            signature.addToBatchSigning(argv[i]);
        }

        //You can set the location and reason of signature by simply changing this strings
        const char* location = "Lisboa, Portugal";
        const char* reason = "Concordo com o conteudo do documento";

        //The page and coordinates where the signature will be printed
        int page = 1;
        double pos_x = 0.1;
        double pos_y = 0.1;
        
        //To actually sign the document you invoke this method, your authentication PIN will be requested
        //After this you can check the signed document in the path provided
        eidCard.SignPDF(signature, page, pos_x, pos_y, location, reason, output_directory);
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