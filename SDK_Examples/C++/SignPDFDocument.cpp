#include "eidlib.h"
#include "eidlibException.h"
#include <iostream>

using namespace eIDMW;

int main(int argc, char **argv) {

    if (argc != 3) 
    {
        std::cout << "Usage: SignPDFDocument [input_file] [output_file]" << std::endl;
        return -1;
    }

    const char* input_file = argv[1];
    const char* output_file = argv[2];

    try 
    {
        //Initialize SDK (must always be called in the beginning of the program)
        PTEID_InitSDK();

        PTEID_EIDCard& eidCard = PTEID_ReaderSet::instance().getReader().getEIDCard();

        //To sign a document you must initialize an instance of PTEID_PDFSignature with the input file
        PTEID_PDFSignature signature(input_file);

        //You can set the various signature levels by calling this method
        signature.setSignatureLevel(PTEID_SignatureLevel::PTEID_LEVEL_TIMESTAMP);

        //You can set the location and reason of signature by providing these string parameters
        const char * location = "Lisboa, Portugal";
        const char * reason = "Concordo com o conteudo do documento";
        
        //The page number where the visible signature seal will be printed
        int page = 1;

        //The location in the page where the visible signature seal will be printed in percentage of page height/width
        //If the values of pos_x and pos_y are negative this becomes an "invisible signature"
        double pos_x = 0.1;
        double pos_y = 0.1;

        //To actually sign the document you invoke this method, and the signature PIN will be requested in an SDK dialog
        eidCard.SignPDF(signature, page, pos_x, pos_y, location, reason, output_file);
    }
    catch (PTEID_ExNoReader &e) 
    {
        std::cerr << "No readers found!" << std::endl;
    }
    catch (PTEID_ExNoCardPresent &e) 
    {
        std::cerr << "No card found in the reader!" << std::endl;
    }
    catch (PTEID_Exception &e) 
    {
        std::cerr << "Caught exception in some SDK method. Error: " << e.GetMessage() << std::endl;
    }
    
    PTEID_ReleaseSDK();
    return 0;
}