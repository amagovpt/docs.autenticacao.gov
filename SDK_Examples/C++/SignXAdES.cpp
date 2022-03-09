#include "eidlib.h"
#include "eidlibException.h"
#include <iostream>

using namespace eIDMW;

int main(int argc, char **argv) {

    try 
    {
        //Initialize SDK (must always be called in the beginning of the program)
        PTEID_InitSDK();

        PTEID_EIDCard& eidCard = PTEID_ReaderSet::instance().getReader().getEIDCard();

        //Name and number of files
        const char *files[] = {"files/input.test", "files/input.pdf"};
        int n_paths = 2; 

        //Output zips
        const char *outputXades = "files/Xades.asic";
        const char *outputXadesT = "files/XadesT.asic";
        const char *outputXadesA = "files/XadesA.asic";
        
        std::cout << "Performing XAdES-B signature (2 input files)" << std::endl;
        //Sign all files with just one basic signature
        eidCard.SignXades(outputXades, files, n_paths);

        std::cout << "Performing XAdES-T signature (2 input files)" << std::endl;
        //Sign all files with just one timestamped signature
        eidCard.SignXadesT(outputXadesT, files, n_paths);
      
        std::cout << "Performing XAdES-LTA signature (2 input files)" << std::endl;
        //Sign all files with type A (archival) signature (includes certificate revocation info and 2 timestamps)
        eidCard.SignXadesA(outputXadesA, files, n_paths);
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
 
    //Release SDK (must always be called at the end of the program)
    PTEID_ReleaseSDK();
    return 0;
}
