#include "eidlib.h"
#include <iostream>
#include <fstream>

using namespace eIDMW;

int main(int argc, char **argv) {

    std::string type_flag = argv[1]; 
    const char* output_file_name = argv[2];

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
        std::cout << "Card found in the reader! Proceeding to get photo. " << std::endl;
        PTEID_EId& eid = PTEID_ReaderSet::instance().getReader().getEIDCard().getID();

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
 
    eIDMW::PTEID_ReleaseSDK();
    return 0;
}            

