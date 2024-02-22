#include "eidlib.h"
#include "eidlibException.h"
#include <iostream>
#include <fstream>

using namespace eIDMW;

int main(int argc, char **argv) {

    if (argc != 3) 
    {
        std::cout << "Usage: GetPhoto [extension] [photo_name]" << std::endl; 
        std::cout << "Extension:" << std::endl; 
        std::cout << "\t-png\t\tSaves photo in png format." << std::endl; 
        std::cout << "\t-jp2\t\tSaves photo in JPEG2000 format." << std::endl; 
        return -1;
    }


    std::string type_flag = argv[1]; 
    const char* output_file_name = argv[2];

    try 
    {
        //Initializes SDK (must always be called in the beginning of the program)
        PTEID_InitSDK();

        //Enable test mode if you're using a test card
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
            std::cout << "Insert the Card access number (CAN) for the card in use: ";
            std::cin >> can_str;
            eidCard.initPaceAuthentication(can_str.c_str(), can_str.size(),  PTEID_CardPaceSecretType::PTEID_CARD_SECRET_CAN);
        }

        //Gets the EId objects (with the cards information)
        PTEID_EId& eid = eidCard.getID();

        //Gets the object representing the photograph present in the card
        PTEID_Photo& photoObj = eid.getPhotoObj();

        //Gets the bytes of the photo in JPEG2000 format
        PTEID_ByteArray& praw = photoObj.getphotoRAW(); 
        
        //Gets the bytes of the photo in PNG format
        PTEID_ByteArray& ppng = photoObj.getphoto();

        std::cout << "Size of PhotoObj:     " << photoObj.getphotoImageinfo().Size() << "\n";
        std::cout << "Size of JPEG2000:     " << praw.Size() << "\n";
        std::cout << "Size of PNG:          " << ppng.Size() << "\n";

        //Saves the PNG or JPEG 2000 format photo (according to the flag set by user), with the name specified by the user
        if (!type_flag.compare("-jp2")) 
        {
            std::ofstream output_file(output_file_name, std::ios::out | std::ios::binary);
            output_file.write((const char *)praw.GetBytes(), praw.Size());
        }
        else if (!type_flag.compare("-png")) 
        {
            std::ofstream output_file(output_file_name, std::ios::out | std::ios::binary);
            output_file.write((const char *)ppng.GetBytes(), ppng.Size());
        }
        else 
        {
            std::cout << "Format not supported" << std::endl;
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


    