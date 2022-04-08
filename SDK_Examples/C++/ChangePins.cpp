#include "eidlib.h"
#include "eidlibException.h"
#include <iostream>

using namespace eIDMW;

int main(int argc, char **argv) {

    if (argc != 2) 
    {
        std::cout << "Usage: ChangePins [pin]" << std::endl; 
        std::cout << "Pin:" << std::endl; 
        std::cout << "\t-auth\t\tChange Authentication Pin." << std::endl; 
        std::cout << "\t-sign\t\tChange Signature Pin." << std::endl; 
        std::cout << "\t-addr\t\tChange Address Pin." << std::endl; 
        return -1;
    }
    std::string type_flag = argv[1];
    int ret = 0;

    try 
    {
        //Initializes SDK (must always be called in the beginning of the program)
        PTEID_InitSDK();

        //Gets the set of readers connected to the system
        PTEID_ReaderSet& readerSet = PTEID_ReaderSet::instance();
        
        //Gets a reader connected to the system (useful if you only have one)
        //Alternatively you can iterate through the readers using getReaderByNum(int index) instead of getReader()
        PTEID_ReaderContext& readerContext = readerSet.getReader();
        
        //Gets the EIDCard and EId objects (with the cards information)
        PTEID_EIDCard& eidCard = readerSet.getReader().getEIDCard();

        //The number of PIN verification tries that the user has (updated with each call to verifyPin or changePin)
        unsigned long triesLeft;

        //Set of the card PINs
        PTEID_Pins& pins = eidCard.getPins();
        unsigned long pinRef = 0;
        
        //Get the specific PIN we want and proceeds to change it
        //ADDR_PIN - Address Pin
        //AUTH_PIN - Authentication Pin
        //SIGN_PIN - Signature Pin
        if (!type_flag.compare("-addr"))
        {
            pinRef = PTEID_Pin::ADDR_PIN;
        } 
        else if (!type_flag.compare("-auth")) 
        {
            pinRef = PTEID_Pin::AUTH_PIN;
        }
        else if (!type_flag.compare("-sign")) 
        {
            pinRef = PTEID_Pin::SIGN_PIN;
        }
        else 
        {
            std::cout << "Pin type doesn't exist!" << std::endl;
            return -1;
        }

        PTEID_Pin& pin = pins.getPinByPinRef(pinRef);

        bool bResult = pin.changePin("","", triesLeft, NULL);

        std::cout << "PIN sucessfully changed?: " << bResult << " Tries left: " << triesLeft << std::endl;

    }
    catch (PTEID_ExNoReader &e) 
    {
        std::cout << "No readers found!" << std::endl;
        ret = -2;
    }
    catch (PTEID_ExNoCardPresent &e) 
    {
        std::cout << "No card found in the reader!" << std::endl;
        ret = -3;
    }
    catch (PTEID_Exception &e) 
    {
        std::cout << "Caught exception in some SDK method. Error: " << e.GetMessage() << std::endl;
        ret = -4;
    }

    //Releases SDK (must always be called at the end of the program)
    PTEID_ReleaseSDK();
    return ret;
}