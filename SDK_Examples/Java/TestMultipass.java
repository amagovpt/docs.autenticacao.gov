import pt.gov.cartaodecidadao.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.io.IOException;


public class TestMultipass {
    //This static block is needed to load the SDK library
    static {
        try {
            System.loadLibrary("pteidlibj");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load. \n" + e);
            System.exit(1);
        }
    }

    public static String bytesToHex(byte[] bytes, long size) {
        return IntStream.range(0, (int)size)
            .mapToObj(i -> String.format("%02x", bytes[i]))
            .collect(Collectors.joining());
    }
    
    public static void main(String[] args) throws IOException, PTEID_Exception {
        try {
            PTEID_ReaderSet.initSDK();
            //Test mode needs test CA certificates in the appropriate location
            //Read SDK manual for more details
            PTEID_Config.SetTestMode(true);
            
            PTEID_ReaderSet readerSet = PTEID_ReaderSet.instance();
            PTEID_ReaderContext reader = readerSet.getReader();
            
            // Read Multipass token data from card using contactless interface
            PTEID_ByteArray token = reader.getMultiPassToken();
            
            // Display token in hexadecimal format
            System.out.println("Token: " + bytesToHex(token.GetBytes(), token.Size()));
        }
        catch(PTEID_Exception e) {
            System.err.println("Failed to read multipass token! Error: "+e.GetMessage());
            PTEID_ReaderSet.releaseSDK();
            return;
        }
        finally {
            PTEID_ReaderSet.releaseSDK();
        }
    }
}
