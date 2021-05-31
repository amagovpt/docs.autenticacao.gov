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


    