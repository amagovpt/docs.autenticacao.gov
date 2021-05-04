#include "eidlib.h"
#include <iostream>

using namespace eIDMW;

int main(int argc, char **argv) {

    if (argc < 3) 
    {
        std::cout << "Incorrect usage. Should pass at least 3 arguments." << std::endl;
        std::cout << "The first is the output directory and the others are the names of the documents to sign." << std::endl;
        return -1;
    }

    //Initializes SDK (must always be called in the beginning of the program)
    eIDMW::PTEID_InitSDK();

    //As the name indicates, gets the number of connected readers, exits the program if no readers were found
    //Also checks if there is a card present, if not exits the program
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
 
    //Releases SDK (must always be called at the end of the program)
    eIDMW::PTEID_ReleaseSDK();
}