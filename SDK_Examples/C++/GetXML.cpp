#include "eidlib.h"
#include "eidlibException.h"
#include <iostream>
#include <fstream>
#include <cstring>

using namespace eIDMW;

int main(int argc, char **argv) {

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

        //Initializes request of XML information
        PTEID_XmlUserRequestedInfo requestedInfo;

        //The number of tries that the user has (updated with each call to verifyPin)
        unsigned long triesLeft;

        //Sets of the card PINs
        PTEID_Pins& pins = eidCard.getPins();
        
        //Gets the specific PIN we want
        //ADDR_PIN - Address Pin
        //AUTH_PIN - Authentication Pin
        //SIGN_PIN - Signature Pin
        PTEID_Pin& pin = pins.getPinByPinRef(PTEID_Pin::ADDR_PIN);

        //If the method verifyPin is called with "" as the first argument it prompts the middleware GUI for the user to insert its PIN
        //Otherwise we can send the PIN as the first argument and the end result will be the same
        if (pin.verifyPin("", triesLeft, true)){

            //Selects information to be requested in XML format
            //You can add or remove fields at will
            std::vector<XMLUserData> data{ XML_PHOTO, XML_NAME,	XML_GIVEN_NAME,	XML_SURNAME, XML_NIC, XML_EXPIRY_DATE, XML_GENDER, XML_HEIGHT,	
                XML_NATIONALITY, XML_DATE_OF_BIRTH,	XML_GIVEN_NAME_FATHER, XML_SURNAME_FATHER, XML_GIVEN_NAME_MOTHER, XML_SURNAME_MOTHER,
                XML_ACCIDENTAL_INDICATIONS, XML_DOCUMENT_NO, XML_TAX_NO, XML_SOCIAL_SECURITY_NO, XML_HEALTH_NO, XML_MRZ1, XML_MRZ2, XML_MRZ3,
                XML_CARD_VERSION, XML_CARD_NUMBER_PAN, XML_ISSUING_DATE, XML_ISSUING_ENTITY, XML_DOCUMENT_TYPE, XML_LOCAL_OF_REQUEST, XML_VERSION,	
                XML_DISTRICT, XML_MUNICIPALITY,	XML_CIVIL_PARISH, XML_ABBR_STREET_TYPE,	XML_STREET_TYPE, XML_STREET_NAME, XML_ABBR_BUILDING_TYPE,	
                XML_BUILDING_TYPE, XML_DOOR_NO,	XML_FLOOR, XML_SIDE, XML_PLACE,	XML_LOCALITY, XML_ZIP4,	XML_ZIP3, XML_POSTAL_LOCALITY, 
                XML_PERSONAL_NOTES,	XML_FOREIGN_COUNTRY, XML_FOREIGN_ADDRESS, XML_FOREIGN_CITY,	XML_FOREIGN_REGION,	XML_FOREIGN_LOCALITY, XML_FOREIGN_POSTAL_CODE};

            //Adds each field of the vector to the request
            for (XMLUserData field : data) {
                requestedInfo.add(field);
            }

            //Gets the XML information from the card and transforms it to a string
            PTEID_CCXML_Doc &ccxml = eidCard.getXmlCCDoc(requestedInfo);
            const char * resultXml = ccxml.getCCXML();

            //Saves information in XML file
            std::ofstream output_file("files/info.xml", std::ios::out);
            output_file.write(resultXml, strlen(resultXml));
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