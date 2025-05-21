import pt.gov.cartaodecidadao.*;

import java.util.stream.Collectors;
import java.io.IOException;
import java.util.stream.IntStream;


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

    // Método para converter bytes em representação hexadecimal
    public static String bytesToHex(byte[] bytes, long size) {
        return IntStream.range(0, (int)size)
            .mapToObj(i -> String.format("%02x", bytes[i]))
            .collect(Collectors.joining());
    }
    
    public static void main(String[] args) throws IOException, PTEID_Exception {
        try {
            // Inicializa o SDK
            PTEID_ReaderSet.initSDK();
            PTEID_Config.SetTestMode(true);
            
            // Obtém o conjunto de leitores disponíveis
            PTEID_ReaderSet readerSet = PTEID_ReaderSet.instance();
            
            // Obtém o primeiro leitor
            PTEID_ReaderContext reader = readerSet.getReader();
            
            // Lê o multipass token do cartão
            PTEID_ByteArray token = reader.getMultiPassToken();
            
            // Mostra o token em formato hexadecimal
            System.out.println("Token: " + bytesToHex(token.GetBytes(), token.Size()));
        }
        catch(PTEID_Exception e) {
            // Trata erros na leitura do token
            System.err.println("Failed to read multipass token! Error: "+e.GetMessage());
            PTEID_ReaderSet.releaseSDK();
            return;
        }
        finally {
            // Garante que os recursos são libertados mesmo em caso de erro
            PTEID_ReaderSet.releaseSDK();
        }
    }
}
