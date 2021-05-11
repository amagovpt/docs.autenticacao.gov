#include "eidlib.h"
#include "eidlibException.h"
#include <iostream>

using namespace eIDMW;

int main(int argc, char **argv) {

    if (argc != 2) 
    {
        std::cout << "Incorrect usage. Should be:\n./ChangePins [-auth|-addr|-sign]" << std::endl;
        return -1;
    }

    std::string type_flag = argv[1]; 

    try 
    {
        //Initializes SDK (must always be called in the beginning of the program)
        PTEID_InitSDK();

        //Gets the set of readers connected to the system
        PTEID_ReaderSet& readerSet = PTEID_ReaderSet::instance();
        
        //Gets a reader connected to the system (useful if you only have one)
        //Alternatively you can iterate through the readers using getReaderByNum(int index) instead of getReader()
        PTEID_ReaderContext& readerContext = PTEID_ReaderSet::instance().getReader();
        
        //Gets the EIDCard and EId objects (with the cards information)
        PTEID_EIDCard& eidCard = PTEID_ReaderSet::instance().getReader().getEIDCard();
        PTEID_EId& eid = PTEID_ReaderSet::instance().getReader().getEIDCard().getID();

        //The number of tries that the user has (updated with each call to verifyPin)
        unsigned long triesLeft;

        //Sets of the card PINs
        PTEID_Pins& pins = eidCard.getPins();
        
        //Gets the specific PIN we want and proceeds to change it
        //ADDR_PIN - Address Pin
        //AUTH_PIN - Authentication Pin
        //SIGN_PIN - Signature Pin
        if (!type_flag.compare("-addr"))
        {
            PTEID_Pin& pin_addr = pins.getPinByPinRef(PTEID_Pin::ADDR_PIN);
            bool bResult = pin_addr.changePin("","", triesLeft, pin_addr.getLabel());
        } 
        else if (!type_flag.compare("-auth")) 
        {
            PTEID_Pin& pin_auth = pins.getPinByPinRef(PTEID_Pin::AUTH_PIN);
            bool bResult = pin_auth.changePin("","", triesLeft, pin_auth.getLabel());
        }
        else if (!type_flag.compare("-sign")) 
        {
            PTEID_Pin& pin_sign = pins.getPinByPinRef(PTEID_Pin::SIGN_PIN);
            bool bResult = pin_sign.changePin("","", triesLeft, pin_sign.getLabel());
        }
        else 
        {
            std::cout << "Flag doesn't exist" << std::endl;
        }
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