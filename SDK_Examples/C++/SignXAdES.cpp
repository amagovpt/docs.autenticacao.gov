#include "eidlib.h"
#include "eidlibException.h"
#include <iostream>

using namespace eIDMW;

int main(int argc, char **argv) {

    try 
    {
        //Initializes SDK (must always be called in the beginning of the program)
        PTEID_InitSDK();

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