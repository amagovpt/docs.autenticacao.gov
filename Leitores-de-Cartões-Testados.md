# Middleware do Cartão de Cidadão
## Leitores de Cartões Testados

**Leitor utilizado:** Gemalto Ezio Shield Pro (IDBridge CT710)
**Tipo de leitor:** PinPad

| **Sistema operativo** | **32-64 bits** | **Nome e versão do driver** | **CC1**  | **CC2** |
|-------- |---- |--------------------------------------- |------ |  ------ |
| Windows 7 | 32 |Device Description: Gemalto Ezio Shield PinPad   / Driver Name: GemCCID.sys / Driver Version: 4.1.4.0 2016  |OK | OK | 
| Windows 7 | 64 |Device Description: Gemalto Ezio Shield PinPad   / Driver Name: GemCCID.sys / Driver Version: 4.1.4.0 2016  |OK | OK | 
| Windows 10 | 32 |Device Description: Gemalto Ezio Shield PinPad  / Driver Name: GemCCID.sys / Driver Version: 4.1.4.0 2016  |OK | OK | 
| Windows 10 | 64 |Device Description: Gemalto Ezio Shield PinPad  / Driver Name: GemCCID.sys / Driver Version: 4.1.4.0 2016  |OK  | OK  | 
| Ubuntu 22  | 64 | CCID 1.5.0 | OK | OK | 
| MacOS Sonoma | ARM64 | CCID (driver pré-instalado no MacOS) | OK | OK |

**Leitor utilizado:** Lifetech LFCRD011
**Tipo de leitor:** Dual-Interface

| **Sistema operativo** | **32-64 bits** | **Nome e versão do driver** | **CC1**  | **CC2** | **Notas** |
|-------- |---- |--------------------------------------- |------ |------ | ------ |
| Windows 10 | 64 |Device Description: Microsoft Usbccid Smartcard Reader (WUDF) / Driver Name: wudfusbcciddriver.inf / Driver Version: 10.0.22621.1 | OK | OK | Testado com ambas as interfaces para o CC2 |
| Ubuntu 24 | 64 | CCID 1.5.5 | OK | OK | Testado com ambas as interfaces para o CC2 |
| Fedora 39 | 64 | CCID 1.5.5 | OK | OK | Testado com ambas as interfaces para o CC2 |
| MacOS Sonoma | 64 | CCID (driver pré-instalado no MacOS) | OK | OK | Testado com ambas as interfaces para o CC2 |

**Leitor utilizado:** Ewent EW1052
**Tipo de leitor:** Simples

| **Sistema operativo** | **32-64 bits** | **Nome e versão do driver** | **CC1** | **CC2** |
|-------- |---- |--------------------------------------- |------ |------ | 
| Windows 10 | 32 |Device Description: Microsoft Usbccid Smartcard Reader (WUDF) / Driver Name: wudfusbcciddriver.inf / Driver Version: 10.0.19041.1  | OK | OK |
| Windows 10 | 64 |Device Description: Microsoft Usbccid Smartcard Reader (WUDF) / Driver Name: wudfusbcciddriver.inf / Driver Version: 10.0.19041.1  | OK | OK |
| MacOS Sonoma | 64 | CCID (driver pré-instalado no MacOS) | OK | OK |

**Leitor utilizado:** Identiv uTrust 4701
**Tipo de leitor:** Dual-Interface

| **Sistema operativo** | **32-64 bits** | **Nome e versão do driver** | **CC1**  | **CC2** | **Notas** |
|-------- |---- |--------------------------------------- |------ |------ | ------ |
| Windows 7    | 32 |Device Description: Microsoft Usbccid Smartcard Reader (WUDF) / Driver Name: wudfusbcciddriver.inf / Driver Version: 6.1.7601.18459 | OK | OK | Testado com ambas as interfaces para o CC2 |
| Windows 10   | 64 |Device Description: Microsoft Usbccid Smartcard Reader (WUDF) / Driver Name: wudfusbcciddriver.inf / Driver Version: 10.0.22621.1 | OK | OK | Testado com ambas as interfaces para o CC2 |
| Ubuntu 22    | 64 | CCID 1.5.0 | OK | OK | Testado com ambas as interfaces para o CC2 |
| Fedora 39    | 64 | CCID 1.5.5 | OK | OK | Testado com ambas as interfaces para o CC2 |
| MacOS Sonoma | 64 | Driver disponivel em `https://support.identiv.com/4701f/` | OK | OK | Testado com ambas as interfaces para o CC2 |

**NOTA**: O leitor Identiv uTrust 4701 apresenta por vezes erros de comunicação com cartões CC2 em modo contactless que resultam no leitor desligar-se a meio da utilização. Esta situação não acontece quando se aproxima o cartão a uma distância de alguns centímetros em vez de ser colocado diretamente em cima do leitor.

**Leitor utilizado:** HID OMNIKEY 5022
**Tipo de leitor:** Contactless

| **Sistema operativo** | **32-64 bits** | **Nome e versão do driver** | **CC2** | **Notas** |
|---------------------- |--------------- |------------------------------- |------ | ------ |
| Windows 10 | 64 | - | OK |  |

**Leitor utilizado:** Trust Ceto SmartCard Reader
**Tipo de leitor:** Contactless

| **Sistema operativo** | **32-64 bits** | **Nome e versão do driver** | **CC2** | **Notas** |
|-------- |---- |--------------------------------------- |------ |------ |
| Windows 10 | 64 |Device Description: Microsoft Usbccid Smartcard Reader (WUDF) / Driver Name: wudfusbcciddriver.inf / Driver Version: 10.0.22621.1 | NOK | Problemas de comunicação |
| Ubuntu 22 | 64 |CCID 1.5.5 | NOK | Problemas de comunicação |

**Leitor utilizado:** Bit4ID miniLector EVO
**Tipo de leitor:** Simples

| **Sistema operativo** | **32-64 bits** | **Nome e versão do driver**   | **Resultado leitura e assinatura** | 
|-------- |---- |--------------------------------------- |------ |
| Windows 7 | 32 |Device Description: Microsoft Usbccid Smartcard Reader (WUDF) / Driver Name: wudfusbcciddriver.inf / Driver Version: 6.1.7601.18459  | (OK / OK) |
| Windows 7 | 64 | Device Description: Microsoft Usbccid Smartcard Reader (WUDF) / Driver Name: wudfusbcciddriver.inf / Driver Version: 6.1.7600.16388 | (OK / OK) |
| Windows 10 | 32 |Microsoft Usbccid Smartcard Reader (WUDF) / Driver Name: wudfusbcciddriver.inf / Driver Version: 10.0.18362.1    | (OK / OK) |
| Windows 10 | 64 |Leitor de Smartcard Microsoft Usbccid (WUDF) / Driver Name: wudfusbcciddriver.inf / Driver Version: 10.0.17763.1 | (OK / OK) |
| Ubuntu 18 | 64 |pcsc-lite version 1.8.23 | (OK / OK) |
| MacOS Catalina | 64 |CCID 1.4.31         | (OK / OK) |

**Leitor utilizado:** Gemalto IDBridge CT30 (legacy name: PC USB TR) 
**Tipo de leitor:** Simples

| **Sistema operativo** | **32-64 bits** | **Nome e versão do driver** | **Resultado leitura e assinatura** |
|-------- |---- |--------------------------------------- |------ | 
| Windows 7 | 32 |Device Description: USB Smart Card Reader  / Driver Name: GemCCID.sys / Driver Version: 4.1.4.0 2016  | (OK / OK) |
| Windows 7 | 64 |Device Description: USB Smart Card Reader  / Driver Name: GemCCID.sys / Driver Version: 4.1.4.0 2016  | (OK / OK) |
| Windows 10 | 32 |Device Description: USB Smart Card Reader  / Driver Name: GemCCID.sys / Driver Version: 4.1.4.0 2016 | (OK / OK) |
| Windows 10 | 64 |Device Description: USB Smart Card Reader  / Driver Name: GemCCID.sys / Driver Version: 4.1.4.0 2016 | (OK / OK) |
| Ubuntu 18 | 64 |pcsc-lite version 1.8.23 | (OK / OK) |
| MacOS Catalina | 64 |CCID 1.4.31         | (OK / OK) |

**Leitor utilizado:** HP KUS0133
**Tipo de leitor:** Teclado com leitor integrado

| **Sistema operativo** | **32-64 bits** | **Nome e versão do driver** | **Resultado leitura e assinatura** |
|-------- |---- |--------------------------------------- |------ | 
| Windows 7 | 32 |Device Description: HP USB Smart Card Keyboard  / Driver Name: HPKBCCID.sys / Driver Version: 4.35.0.2 04-03-2013  | (OK / OK) |
| Windows 7 | 64 |Device Description: HP USB Smart Card Keyboard  / Driver Name: HPKBx64.sys / Driver Version: 4.35.0.2 04-03-2013   | (OK / OK) |
| Windows 10 | 32 |Device Description: HP USB Smart Card Keyboard  / Driver Name: HPKBCCID.sys / Driver Version: 4.34.0.1 06-03-2012 | (OK / OK) |
| Windows 10 | 64 |Device Description: HP USB Smart Card Keyboard  / Driver Name: HPKBx64.sys / Driver Version: 4.35.0.2 04-03-2013  | (OK / OK) |
| Ubuntu 18 | 64 |pcsc-lite version 1.8.23 | (OK / OK) |
| MacOS Catalina | 64 |CCID 1.4.31         | (OK / OK) |

Atualizado em 08 de Maio 2024
