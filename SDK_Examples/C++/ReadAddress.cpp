#include "eidlib.h"
#include "eidlibException.h"
#include <iostream>

using namespace eIDMW;

int main(int argc, char **argv) {

    try 
    {
        //Initializes SDK (must always be called in the beginning of the program)
        PTEID_InitSDK();

        //Get the set of readers connected to the system
        PTEID_ReaderSet& readerSet = PTEID_ReaderSet::instance();
        
        //Get a reader connected to the system (useful if you only have one)
        //Alternatively you can iterate through the readers using getReaderByNum(int index) instead of getReader()
        PTEID_ReaderContext& readerContext = PTEID_ReaderSet::instance().getReader();
        
        //Get the EIDCard and EId objects (with the cards information)
        PTEID_EIDCard& eidCard = readerContext.getEIDCard();
        PTEID_EId& eid = eidCard.getID();

        //The number of tries that the user has (updated with each call to verifyPin)
        unsigned long triesLeft;

        //Set of card PINs
        PTEID_Pins& pins = eidCard.getPins();
        
        //Get the specific PIN object needed
        //ADDR_PIN - Address Pin
        //AUTH_PIN - Authentication Pin
        //SIGN_PIN - Signature Pin
        PTEID_Pin& pin = pins.getPinByPinRef(PTEID_Pin::ADDR_PIN);

        //If method PTEID_Pin::verifyPin() is called with "" as the first argument it prompts the middleware GUI for the user to insert its PIN
        //Otherwise we can send the PIN value as the first argument and avoid the standard dialog
        if (pin.verifyPin("", triesLeft, true)) {

            //SDK class that handles address related information
            PTEID_Address& address = eidCard.getAddr();

            std::cout << "\n\nReading address details of: " << eid.getGivenName() << " " << eid.getSurname() << std::endl;
            std::cout << "Country:                        " << address.getCountryCode() << std::endl;
            std::cout << "District:                       " << address.getDistrict() << std::endl;
            std::cout << "District (code):                " << address.getDistrictCode() << std::endl;
            std::cout << "Municipality:                   " << address.getMunicipality() << std::endl;
            std::cout << "Municipality (code):            " << address.getMunicipalityCode() << std::endl;   
            std::cout << "Parish:                         " << address.getCivilParish() << std::endl;
            std::cout << "Parish (code):                  " << address.getCivilParishCode() << std::endl;
            std::cout << "Street Type (Abbreviated):      " << address.getAbbrStreetType() << std::endl;
            std::cout << "Street Type:                    " << address.getStreetType() << std::endl;
            std::cout << "Street Name:                    " << address.getStreetName() << std::endl;
            std::cout << "Building Type (Abbreviated):    " << address.getAbbrBuildingType() << std::endl;
            std::cout << "Building Type:                  " << address.getBuildingType() << std::endl;
            std::cout << "Door nº:                        " << address.getDoorNo() << std::endl;
            std::cout << "Floor:                          " << address.getFloor() << std::endl;
            std::cout << "Side:                           " << address.getSide () << std::endl;
            std::cout << "Locality:                       " << address.getLocality() << std::endl;
            std::cout << "Place:                          " << address.getPlace() << std::endl;
            std::cout << "Postal code:                    " << address.getZip4() << "-" << address.getZip3() << std::endl;
            std::cout << "Postal Locality:                " << address.getPostalLocality() << std::endl;
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