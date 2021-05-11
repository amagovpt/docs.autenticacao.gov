#include "eidlib.h"
#include <iostream>

using namespace eIDMW;

int main(int argc, char **argv) {

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

        //Name and number of files
        const char *files[] = {"files/input.test", "files/input.pdf"};
        int n_paths = 2; 

        //Output zips
        const char *outputXades = "files/Xades.zip";
        const char *outputXadesT = "files/XadesT.zip";
        const char *outputXadesA = "files/XadesA.zip";

        //Sign all files with unique signature          
        eidCard.SignXades(outputXades, files, n_paths);

        //Sign all files with unique signature including timestamp            
        eidCard.SignXadesT(outputXadesT, files, n_paths);
      
        //Sign all files with type A (archival) unique signature 
        eidCard.SignXadesA(outputXadesA, files, n_paths);
    }
 
    //Releases SDK (must always be called at the end of the program)
    eIDMW::PTEID_ReleaseSDK();
}