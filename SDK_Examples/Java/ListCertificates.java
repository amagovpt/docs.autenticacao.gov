import pt.gov.cartaodecidadao.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.io.ByteArrayInputStream;

public class ListCertificates {

	//This static block is needed to load the SDK native library
    static {
        try {
            System.loadLibrary("pteidlibj");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load. \n" + e);
            System.exit(1);
        }
    }    

	public static void releaseSDK() {

        try {
            PTEID_ReaderSet.releaseSDK();

        } catch (PTEID_Exception ex) {
            System.out.println("Caught exception in releaseSDK(). Error: " + ex.GetMessage());
        }
    }

    private static Certificate loadCertificateFromDEREncoding(byte[] data) {
        ByteArrayInputStream ba_is;
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            ba_is = new ByteArrayInputStream(data);
            return cf.generateCertificate(ba_is);

        } catch (CertificateException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static PTEID_EIDCard startReading(boolean test_mode) throws PTEID_Exception {
    	//Must always be called in the beginning of the program
        PTEID_ReaderSet.initSDK();

        //Enable test mode if you're using a test card
        PTEID_Config.SetTestMode(test_mode);

        //Gets the set of connected readers
        PTEID_ReaderSet readerSet = PTEID_ReaderSet.instance();
        PTEID_ReaderContext readerContext = readerSet.getReader();

        //Gets the Card Contact Interface and type
        if (readerContext.isCardPresent()) {
            PTEID_CardContactInterface contactInterface = readerContext.getCardContactInterface();
            System.out.println("Contact Interface:" + (contactInterface == PTEID_CardContactInterface.PTEID_CARD_CONTACTLESS ? "CONTACTLESS" : "CONTACT"));
        }

		return readerContext.getEIDCard();
    }

    private static void listCertificates(PTEID_EIDCard card) throws PTEID_Exception {

    	System.out.println("Building signature certificate chain...");
        PTEID_Certificates certs = card.getCertificates();
        
        PTEID_Certificate current_cert = certs.getCert(PTEID_CertifType.PTEID_CERTIF_TYPE_SIGNATURE);

        byte[] cert_data = current_cert.getCertData().GetBytes();
        
        Certificate java_cert = loadCertificateFromDEREncoding(cert_data);
        System.out.println("Certificate public key algorithm: "+ java_cert.getPublicKey().getAlgorithm());

        System.out.format("Cert level 0: subject: %-66s validityEnd: %s\n", current_cert.getOwnerName(), current_cert.getValidityEnd());

        int level = 1;
        while(!current_cert.isRoot()) {
        	PTEID_Certificate next_cert = current_cert.getIssuer();
        	System.out.format("Cert level %d: subject: %-66s validityEnd: %s\n", level, next_cert.getOwnerName(), next_cert.getValidityEnd());
        	current_cert = next_cert;
        	level++;
        }
    }

    private static void startListing(boolean test_mode) {

        try {
            PTEID_EIDCard card = startReading(test_mode);
            listCertificates(card);
        } 
        catch (PTEID_ExNoReader ex) {
            System.out.println("No reader found.");
        } 
        catch (PTEID_ExNoCardPresent ex) {
            System.out.println("No card inserted.");
        } 
        catch (PTEID_Exception ex) {
            System.out.println("Caught exception in some SDK method. Error: " + ex.GetMessage());
        }
        finally {
            releaseSDK();
        }
    }
	
	public static void main(String[] args) {
		startListing(args.length == 1 && args[0].equals("TEST"));
	}

}