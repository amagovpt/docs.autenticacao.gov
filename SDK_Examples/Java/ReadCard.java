import java.util.logging.Level;
import java.util.logging.Logger;

import pt.gov.cartaodecidadao.*;


public class ReadCard {

    //This static block is needed to load the SDK library
    static {
        try {
            System.loadLibrary("pteidlibj");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load. \n" + e);
            System.exit(1);
        }
    }
    
    //Main attributes needed for SDK functionalities
    PTEID_ReaderSet readerSet = null;
    PTEID_ReaderContext readerContext = null;
    PTEID_EIDCard eidCard = null;
    PTEID_EId eid = null;


    /**
     * Initializes the SDK and sets main variables
     * @throws PTEID_Exception when there is some error with the SDK methods
     */
    public void initiate() throws PTEID_Exception {
       
        //Must always be called in the beginning of the program
        PTEID_ReaderSet.initSDK();

        //Gets the set of connected readers
        readerSet = PTEID_ReaderSet.instance();

        //Gets the first reader
        //When multiple readers are connected, you should iterate through the various indexes with the methods getReaderName and getReaderByName
        readerContext = readerSet.getReader();

        //Gets the card instance
        eidCard = readerContext.getEIDCard();
        eid = eidCard.getID();
    }

    /**
     * Releases the SDK (must always be done at the end of the program)
     */
    public void release() {

        try {
            PTEID_ReaderSet.releaseSDK();

        } catch (PTEID_Exception ex) {
            Logger.getLogger(ReadCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prints main info present in the card (that doesn't need address pin)
     * @throws PTEID_Exception when there is some error with the SDK methods
     */
    public void showCardInfo() throws PTEID_Exception {

        System.out.println("\n\nReading card details:");
        System.out.println("Name:                       " + eid.getGivenName() + " " + eid.getSurname());
        System.out.println("Card Type:                  " + eid.getDocumentType());
        System.out.println("Card Version:               " + eid.getDocumentVersion());
        System.out.println("Validaty Status:            " + eid.getValidation());
        System.out.println("Card Number:                " + eid.getDocumentNumber());
        System.out.println("Local of Request:           " + eid.getLocalofRequest());   
        System.out.println("Issuing Entity:             " + eid.getIssuingEntity());
        System.out.println("Issuing Date:               " + eid.getValidityBeginDate());
        System.out.println("Validity End Date:          " + eid.getValidityEndDate());
        System.out.println("PAN Number:                 " + eid.getDocumentPAN());
        System.out.println("Civilian ID :               " + eid.getCivilianIdNumber());
        System.out.println("Tax ID:                     " + eid.getTaxNo());
        System.out.println("Social Security ID:         " + eid.getSocialSecurityNumber());
        System.out.println("Health ID:                  " + eid.getHealthNumber());
        System.out.println("Parents:                    " + eid.getParents());
        System.out.println("Father:                     " + eid.getGivenNameFather() + " " + eid.getSurnameFather());
        System.out.println("Mother:                     " + eid.getGivenNameMother() + " " + eid.getSurnameMother());
        System.out.println("Indications:                " + eid.getAccidentalIndications());
        System.out.println("Nationality:                " + eid.getNationality());
        System.out.println("Country:                    " + eid.getCountry());
        System.out.println("Date of birth:              " + eid.getDateOfBirth());
        System.out.println("Height:                     " + eid.getHeight());
        System.out.println("Gender:                     " + eid.getGender());
        System.out.println("MRZ:                        " + eid.getMRZ1());
        System.out.println("                            " + eid.getMRZ2());
        System.out.println("                            " + eid.getMRZ3());
        
    }

    public void start() {

        try {

            initiate();
            showCardInfo();

        } catch (PTEID_ExNoReader ex) {
            System.out.println("No reader found.");
        } catch (PTEID_ExNoCardPresent ex) {
            System.out.println("No card inserted.");
        } catch (PTEID_Exception ex) {
            Logger.getLogger(ReadCard.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            release();
        }
    }

    public static void main(String[] args) {
        new ReadCard().start();
    }
}
