## Cartão de Cidadão 2: Multipass Token

**Importante**: Funcionalidade disponível desde a versão 3.14.0 do Middleware

Esta funcionalidade permite a leitura de um identificador único de 16 bytes através de tecnologia contactless. Esta feature está disponível exclusivamente nos novos Cartões de Cidadão. A implementação já integra os protocolos de segurança definidos pelo standard ICAO Doc 9303, incluindo Passive Authentication (validação SOD) e Chip Authentication, garantindo a integridade e autenticidade dos dados e cartão.

### Dados Retornados

O método `getMultiPassToken()` retorna um objeto do tipo `PTEID_ByteArray` contendo 16 bytes de dados. 

### Exceções Possíveis

O método pode lançar exceções do tipo `PTEID_Exception` com os seguintes códigos de erro:

* **EIDMW_ERR_NOT_SUPPORTED** (0xe1d00202): Este erro ocorre quando o cartão não é um Cartão de Cidadão 2. Pode ser retornado para cartões antigos, cartões não portugueses ou cartões não reconhecidos.

* **EIDMW_ERR_FILE_NOT_FOUND** (0xe1d00208): Este erro ocorre quando o cartão é um Cartão de Cidadão 2 que não contem um multipass token.

### Leitura via Event Callback

Além da leitura direta, o multipass token também pode ser obtido através de um EventCallback que pode ser registado para todos os leitores presentes no sistema. Poderá ler sobre callback setup [aqui](https://amagovpt.github.io/docs.autenticacao.gov/manual_sdk.html#eventos-de-inser%C3%A7%C3%A3o--remo%C3%A7%C3%A3o-de-cart%C3%B5es) .

### Notas Importantes

- A função `getMultiPassToken()` só funciona com a tecnologia contactless. No entanto não é preciso input manual de PIN/CAN.
- Pode utilizar o método `PTEID_ReaderContext::getCardType()` para verificar/confirmar o tipo de cartão antes de tentar ler o multipass token.

### Exemplos de Código

#### C++

```cpp
void dumpByteArray(const unsigned char *data, long length) {
    for (int i = 0; i != length; i++)
        printf("%02x", data[i]);
    puts("\n");
}

int main() {
    // Inicializa o SDK
    PTEID_InitSDK();
    
    try {
        // Obtém o conjunto de leitores disponíveis
        PTEID_ReaderSet &readerSet = PTEID_ReaderSet::instance();
        
        // Obtém o primeiro leitor
        PTEID_ReaderContext &readerContext = readerSet.getReader();
        
        // Lê o multipass token do cartão
        PTEID_ByteArray token = readerContext.getMultiPassToken();
        
        // Mostra o token em formato hexadecimal
        printf("Token: ");
        dumpByteArray(token.GetBytes(), token.Size());
    } catch (PTEID_Exception &e) {
        // Trata erros na leitura do token
        printf("Failed to read multipass token! Error: %lx\n", e.GetError());
    }
    
    // Liberta recursos do SDK
    PTEID_ReleaseSDK();
    return 0;
}
```

#### Java

```java
public class TestMultipass {
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
```
