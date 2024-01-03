#include "eidlib.h"
#include "eidlibException.h"
#include <iostream>

using namespace eIDMW;

int main(int argc, char **argv) {

    try 
    {
        //Initialize SDK (must always be called in the beginning of the program)
        PTEID_InitSDK();

        //Sets test mode to true so that CC2 can be tested
        PTEID_Config::SetTestMode(true);

        //Get the set of readers connected to the system
        PTEID_ReaderSet& readerSet = PTEID_ReaderSet::instance();
        
        //Get a reader connected to the system (useful if you only have one)
        //Alternatively you can iterate through the readers using getReaderByNum(int index) instead of getReader()
        PTEID_ReaderContext& readerContext = readerSet.getReader();

        //Gets the Card Contact Interface and type
        PTEID_CardContactInterface contactInterface;
        PTEID_CardType cardType;

        //Gets the Card Contact Interface and type
        if(readerContext.isCardPresent()){
            contactInterface = readerContext.getCardContactInterface();
            cardType = readerContext.getCardType();
            std::cout << "Contact Interface:" << (contactInterface == PTEID_CARD_CONTACTLESS ? "CONTACTLESS" : "CONTACT") << std::endl;
        }
        
        //Get the EIDCard object
        PTEID_EIDCard& eidCard = readerContext.getEIDCard();

        //If the contactInterface is contactless and the card supports contactless then authenticate with PACE
        if (contactInterface == PTEID_CARD_CONTACTLESS && cardType == PTEID_CARDTYPE_IAS5){
            std::string can_str;
            std::cout << "Insert the CAN for this EIDCard: ";
            std::cin >> can_str;
            eidCard.initPaceAuthentication(can_str.c_str(), can_str.size(),  PTEID_CardPaceSecretType::PTEID_CARD_SECRET_CAN);
        }

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
    catch (PTEID_PACE_ERROR &e)
    {
        std::cerr << "Error with contacless protocol. Error: " << e.GetMessage() << std::endl;
    } 
    catch (PTEID_Exception &e) 
    {
        std::cerr << "Caught exception in some SDK method. Error: " << e.GetMessage() << std::endl;
    }

    //Releases SDK (must always be called at the end of the program)
    PTEID_ReleaseSDK();
    return 0;
}