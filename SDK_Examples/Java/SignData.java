import java.io.ByteArrayInputStream;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import java.util.Arrays;
import pt.gov.cartaodecidadao.*;
import java.util.Scanner;

/* 
   This code example demonstrates the raw data signing feature of pteid-mw SDK
   Available algorithm is RSA-SHA256 with PKCS#1 padding, in future versions other algorithm options may be offered.
*/

public class SignData {

    //This static block is needed to load the sdk library
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
    final static String dataToBeSigned = "This is our input data for digital signature";
    PTEID_CardType cardType = null;
    PTEID_CardContactInterface contactInterface = null;


    /**
     * Initializes the SDK and sets main variables
     * @throws PTEID_Exception when there is some error with the SDK methods
     */
    public void initiate() throws PTEID_Exception {
       
        //Must always be called in the beginning of the program
        PTEID_ReaderSet.initSDK();

        //Sets test mode to true so that CC2 can be tested
        PTEID_Config.SetTestMode(true);

        //Gets the set of connected readers
        readerSet = PTEID_ReaderSet.instance();

        //Gets the first reader
        //When multiple readers are connected, you can iterate through the various reader objects with the methods getReaderName and getReaderByName or getReaderByNum
        //Any reader can be checked for an inserted card with PTEID_ReaderContext.isCardPresent()
        readerContext = readerSet.getReader();

        //Gets the Card Contact Interface and type
        if(readerContext.isCardPresent()){
            contactInterface = readerContext.getCardContactInterface();
            cardType = readerContext.getCardType();
            System.out.println("Contact Interface:" + (contactInterface == PTEID_CardContactInterface.PTEID_CARD_CONTACTLESS ? "CONTACTLESS" : "CONTACT"));
        }

        //Gets the card instance
        eidCard = readerContext.getEIDCard();

        //If the contactInterface is contactless and the card supports contactless then authenticate with PACE
        if (contactInterface == PTEID_CardContactInterface.PTEID_CARD_CONTACTLESS && cardType ==  PTEID_CardType.PTEID_CARDTYPE_IAS5){
            Scanner in = new Scanner(System.in);
            System.out.print("Insert the CAN for this EIDCard: ");
            String can_str = in.nextLine();
            eidCard.initPaceAuthentication(can_str, can_str.length(),  PTEID_CardPaceSecretType.PTEID_CARD_SECRET_CAN);
        }
    }
    
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    private PTEID_ByteArray getSignatureInput() {
        MessageDigest digest;
        
        try {
            digest = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-256 digest algorithm not present in this JVM!");
            return null;
        }
        byte[] data_hash = digest.digest(dataToBeSigned.getBytes(StandardCharsets.UTF_8));
        return new PTEID_ByteArray(data_hash, data_hash.length);
    }

    private void verifySignature(Certificate signature_certificate, byte[] card_signature) {
        try {

            PublicKey pk = signature_certificate.getPublicKey();
            String signatureAlgo = pk instanceof RSAPublicKey ? "SHA256withRSA": "SHA256withECDSA";
            Signature sig = Signature.getInstance(signatureAlgo);
            sig.initVerify(pk);
            sig.update(dataToBeSigned.getBytes(StandardCharsets.UTF_8));
            boolean verified = sig.verify(card_signature);

            System.out.format("%s signature is: %s\n", signatureAlgo, (verified ? "OK": "NOK"));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Non-conforming JVM, this algo is present in the Java Security Standard Algorithm Names Specification!");
            e.printStackTrace();
        }
        catch (InvalidKeyException e) {
            System.err.println("Can't use provided public key: "+ e.getMessage());
        }
        catch (SignatureException e) {
            System.err.println("Failed to verify signature!: "+ e.getMessage());
        }
    }

    private Certificate loadCertificateFromDEREncoding(byte[] data) {
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

    
    public void start() {
        
        try {
            initiate();
            //Input byte array must be the SHA-256 digest of the input data in binary format
            PTEID_ByteArray input_ba = getSignatureInput();
            
            if (input_ba != null) {

                //Change to false for Authentication
                boolean isSignature_key = true;
                
                //The following method call is equivalent to eidCard.Sign() with the same input: eidCard.SignSHA256
                PTEID_ByteArray signature = eidCard.Sign(input_ba, isSignature_key);

                System.out.println(String.format("Signature generated by CC card %s key: %s\nInput data: \"%s\"", 
                                   isSignature_key ? "signature" : "authentication", bytesToHex(signature.GetBytes()), dataToBeSigned));

                PTEID_ByteArray cert;

                if (isSignature_key)
                    cert = eidCard.getCert(PTEID_CertifType.PTEID_CERTIF_TYPE_SIGNATURE).getCertData();
                else 
                    cert = eidCard.getCert(PTEID_CertifType.PTEID_CERTIF_TYPE_AUTHENTICATION).getCertData();

                //Read matching certificate from card
                Certificate java_cert = loadCertificateFromDEREncoding(cert.GetBytes());
                if (java_cert != null) {
                    //Verify ECDSA-SHA256 signature using Java security classes
                    if (cardType ==  PTEID_CardType.PTEID_CARDTYPE_IAS5){
                        // If CCv2 then it is required to convert the signature to ASN1 format
                        verifySignature(java_cert, convertToASN1(signature.GetBytes()));
                    }
                    //Verify RSA-SHA256 signature using Java security classes
                    else{
                        verifySignature(java_cert, signature.GetBytes());
                    }
                }
            }

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
        catch (Exception ex) {
            System.out.println("Exception caught: " + ex.getMessage());
        }
        finally {
            release();
        }
    }

     /**
     * Releases the SDK (must always be done at the end of the program)
     */
    public void release() {

        try {
            PTEID_ReaderSet.releaseSDK();
        } catch (PTEID_Exception ex) {
            System.out.println("Caught exception in some SDK method. Error: " + ex.GetMessage());
        }
    }

    public static void main(String[] args) {
        
        new SignData().start();
    }

    public static byte[] convertToASN1(byte[] rawSignature) {
        // Extract r and s components from the raw signature
        int keySize = rawSignature.length / 2;
        byte[] rBytes = Arrays.copyOfRange(rawSignature, 0, keySize);
        byte[] sBytes = Arrays.copyOfRange(rawSignature, keySize, 2 * keySize);

        // Convert r and s to BigIntegers
        BigInteger r = new BigInteger(1, rBytes);
        BigInteger s = new BigInteger(1, sBytes);

        // Encode r and s as ASN.1 INTEGERs
        byte[] asn1Encoding = new byte[encodeASN1Integer(r).length + encodeASN1Integer(s).length];
        System.arraycopy(encodeASN1Integer(r), 0, asn1Encoding, 0, encodeASN1Integer(r).length);
        System.arraycopy(encodeASN1Integer(s), 0, asn1Encoding, encodeASN1Integer(r).length, encodeASN1Integer(s).length);


        // Wrap the encoded integers in an ASN.1 SEQUENCE
        return encodeASN1Sequence(asn1Encoding);
    }

    private static byte[] encodeASN1Integer(BigInteger value) {
        byte[] valueBytes = value.toByteArray();
        if (valueBytes[0] == 0x00) {
            // Remove leading zero byte if present
            valueBytes = Arrays.copyOfRange(valueBytes, 1, valueBytes.length);
        }
        int length = valueBytes.length;
        byte[] encoding = new byte[length + 2];
        encoding[0] = 0x02; // ASN.1 INTEGER tag
        encoding[1] = (byte) length; // Length of INTEGER value
        System.arraycopy(valueBytes, 0, encoding, 2, length);
        return encoding;
    }

    private static byte[] encodeASN1Sequence(byte[] content) {
        int length = content.length;
        byte[] encoding = new byte[length + 2];
        encoding[0] = 0x30; // ASN.1 SEQUENCE tag
        encoding[1] = (byte) length; // Length of SEQUENCE content
        System.arraycopy(content, 0, encoding, 2, length);
        return encoding;
    }
}
