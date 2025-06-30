## Callbacks para Leitores não suportados

Esta funcionalidade permite aos programadores implementar suporte para leitores de cartões que não são nativamente suportados pelo SDK. Através da interface de callbacks `PTEID_CardInterfaceCallbacks`, é possível integrar qualquer leitor de cartões, independentemente do protocolo de comunicação utilizado.

### Limitações

**Nota importante**: Algumas funcionalidades como assinatura digital podem não estar totalmente suportadas quando se utilizam custom callbacks.

### Estrutura da API

A interface baseia-se na estrutura `PTEID_CardInterfaceCallbacks` que define todos os métodos necessários para comunicação com o leitor:

```c
struct PTEID_CardInterfaceCallbacks {
    void *context;
    
    PTEID_EstablishContextFn establishContext;
    PTEID_ReleaseContextFn releaseContext;
    PTEID_ListReadersFn listReaders;
    PTEID_CardPresentInReaderFn cardPresentInReader;
    PTEID_StatusWithATRFn statusWithATR;
    PTEID_ConnectFn connect;
    PTEID_DisconnectFn disconnect;
    PTEID_TransmitFn transmit;
    PTEID_RecoverFn recover;
    PTEID_ControlFn control;
    PTEID_BeginTransactionFn beginTransaction;
    PTEID_EndTransactionFn endTransaction;
};
```

Poderá verificar os types completos no header [`CardCallbacks.h`](https://github.com/amagovpt/autenticacao.gov/blob/master/pteid-mw-pt/_src/eidmw/eidlib/CardCallbacks.h)

### Códigos de Retorno

Todos os métodos de callback devem retornar códigos da família `PTEID_CallbackResult`:

- **PTEID_CALLBACK_OK** (0x00000000): Operação completada com sucesso
- **PTEID_CALLBACK_ERR_INVALID_PARAM** (0xe1d00301): Parâmetro inválido
- **PTEID_CALLBACK_ERR_NO_CARD** (0xe1d00302): Cartão não presente
- **PTEID_CALLBACK_ERR_COMM_ERROR** (0xe1d00303): Erro de comunicação
- **PTEID_CALLBACK_ERR_NO_READER** (0xe1d00304): Leitor não encontrado
- **PTEID_CALLBACK_ERR_TIMEOUT** (0xe1d00305): Operação expirou
- **PTEID_CALLBACK_ERR_ACCESS_DENIED** (0xe1d00306): Acesso negado
- **PTEID_CALLBACK_ERR_NOT_SUPPORTED** (0xe1d00307): Operação não suportada
- **PTEID_CALLBACK_ERR_BUFFER_TOO_SMALL** (0xe1d00308): Buffer insuficiente
- **PTEID_CALLBACK_ERR_CARD_REMOVED** (0xe1d00309): Cartão removido
- **PTEID_CALLBACK_ERR_UNRESPONSIVE** (0xe1d0030A): Cartão não responde
- **PTEID_CALLBACK_ERR_GENERIC** (0xe1d003FF): Erro genérico

### Inicialização

Para utilizar callbacks, utilize `PTEID_ReaderSet::initSDKWithCallbacks()` em vez de `PTEID_InitSDK()` para inicializar o SDK:

```cpp
PTEID_CardInterfaceCallbacks callbacks = {
    .context = &myImplementationObject,
    .establishContext = MyClass::EstablishContext,
    .releaseContext = MyClass::ReleaseContext,
    // ... outros callbacks
};

PTEID_ReaderSet::initSDKWithCallbacks(callbacks);
```

### Notas Importantes

- O campo `context` pode ser utilizado para passar dados específicos da implementação
- Thread safety é garantida pela SDK
- Recomenda-se validação rigorosa de todos os parâmetros

### Exemplo de Implementação

Para um exemplo de implementação completo e funcional baseado na API PCSC poderá consultar o seguinte programa: [APDU Callbacks example code](https://github.com/amagovpt/docs.autenticacao.gov/blob/main/multipass/apdu_callbacks.cpp)

#### C++

```cpp
#include <eidlib.h>
#include "CardCallbacks.h"

class MyReaderInterface {
public:
    static PTEID_CallbackResult EstablishContext(void *context) {
        // Implementar inicialização do leitor
        return PTEID_CALLBACK_OK;
    }
    
    static PTEID_CallbackResult ReleaseContext(void *context) {
        // Implementar limpeza de recursos
        return PTEID_CALLBACK_OK;
    }
    
    // Implementar outros métodos obrigatórios...
};

int main() {
    MyReaderInterface interface;
    
    PTEID_CardInterfaceCallbacks callbacks = {
        .context = &interface,  // Pode ser qualquer ponteiro para dados específicos
        .establishContext = MyReaderInterface::EstablishContext,
        .releaseContext = MyReaderInterface::ReleaseContext,
        .listReaders = MyReaderInterface::ListReaders,
        .cardPresentInReader = MyReaderInterface::CardPresentInReader,
        .connect = MyReaderInterface::Connect,
        .disconnect = MyReaderInterface::Disconnect,
        .transmit = MyReaderInterface::Transmit,
        // ... outros callbacks necessários
    };

    // Inicializar SDK com custom callbacks
    PTEID_ReaderSet::initSDKWithCallbacks(callbacks);
    
    try {
        // Utilizar SDK normalmente
        PTEID_ReaderSet &readerSet = PTEID_ReaderSet::instance();
        PTEID_ReaderContext &readerContext = readerSet.getReader();
        
        // Exemplo: ler token multipass
        PTEID_ByteArray token = readerContext.getMultiPassToken();
        
    } catch (PTEID_Exception &e) {
        printf("Erro na leitura: %lx\n", e.GetError());
    }
    
    PTEID_ReleaseSDK();
    return 0;
}
```