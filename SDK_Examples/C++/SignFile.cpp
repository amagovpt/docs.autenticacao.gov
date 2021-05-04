#include "eidlib.h"
#include <iostream>

using namespace eIDMW;

int main(int argc, char **argv) {

    if (argc != 3) 
    {
        std::cout << "Incorrect usage. Should be:\n./sign_file #input_file #output_file" << std::endl;
        return -1;
    }

    eIDMW::PTEID_InitSDK();

    const char* input_file = argv[1];
    const char* output_file = argv[2];

    if (PTEID_ReaderSet::instance().readerCount() == 0) 
    {
		std::cout << "No readers found!" << std::endl;
    }
    else if (!PTEID_ReaderSet::instance().getReader().isCardPresent())
	{
		std::cout << "No card found in the reader!" << std::endl;
	}
    else 
    {
        std::cout << "Card found in the reader! Proceeding to sign the document. " << std::endl;
        PTEID_EIDCard& eidCard = PTEID_ReaderSet::instance().getReader().getEIDCard();


        PTEID_PDFSignature signature(input_file);        
        signature.setSignatureLevel(PTEID_SignatureLevel::PTEID_LEVEL_TIMESTAMP);

        const char * location = "Lisboa, Portugal";
        const char * reason = "Concordo com o conteudo do documento";
        int page = 1;

        double pos_x = 0.1;
        double pos_y = 0.1;
        eidCard.SignPDF(signature, page, pos_x, pos_y, location, reason, output_file);
    }
 
    eIDMW::PTEID_ReleaseSDK();
    return 0;
}

