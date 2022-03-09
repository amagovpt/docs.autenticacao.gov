#include "eidlib.h"
#include "eidlibException.h"
#include <iostream>

using namespace eIDMW;

int main(int argc, char **argv) {

    try 
    {
        //Initialize SDK (must always be called in the beginning of the program)
        PTEID_InitSDK();

        //Get the set of readers connected to the system
        PTEID_ReaderSet& readerSet = PTEID_ReaderSet::instance();
        
        //Get a reader connected to the system (useful if you only have one)
        //Alternatively you can iterate through the readers using getReaderByNum(int index) instead of getReader()
        PTEID_ReaderContext& readerContext = PTEID_ReaderSet::instance().getReader();
        
        //Get the EIDCard object
        PTEID_EIDCard& eidCard = readerContext.getEIDCard();

        //Obtain the Pins object (with informations of all pins)
        PTEID_Pins& pins = eidCard.getPins();

        std::cout << "List of card PINs and respective \"tries left\":" << std::endl;

        //Iterates through all pins and prints the number of tries left for the user to type the correct pin 
        for (int i = 0; i < pins.count(); i++) {
            PTEID_Pin& pin = pins.getPinByNumber(i);

            std::cout << pin.getLabel() << ":\nTries: " << pin.getTriesLeft() << std::endl;
        }
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

    //Releases SDK (must always be called at the end of the program)
    PTEID_ReleaseSDK();
    return 0;
}