# Manual da Aplicação Autenticação.gov para computador <!-- omit in toc -->

![Ilustração: Cartão de Cidadão](Pictures/CartaoCidadao.png "Cartão de Cidadão"){:.center}

![Ilustração: Aplicação Autenticação.gov](Pictures/Autenticacao.Gov_Home.png "Aplicação Autenticação.gov"){:.center}


# Tabela de conteúdos <!-- omit in toc -->

- [Introdução](#introdução)
- [Download, instalação e remoção da aplicação](#download-instalação-e-remoção-da-aplicação)
  - [Sistemas Operativos oficialmente suportados](#sistemas-operativos-oficialmente-suportados)
  - [Download do pacote de instalação da aplicação](#download-do-pacote-de-instalação-da-aplicação)
  - [Instalação da aplicação](#instalação-da-aplicação)
    - [Instalação em Microsoft Windows](#instalação-em-microsoft-windows)
    - [Instalação em Linux](#instalação-em-linux)
      - [Instalação através do *Software Center*](#instalação-através-do-software-center)
      - [Instalação através da linha de comandos](#instalação-através-da-linha-de-comandos)
    - [Instalação em Mac OS](#instalação-em-mac-os)
  - [Remoção da aplicação](#remoção-da-aplicação)
    - [Remoção em *Microsoft Windows 7*](#remoção-em-microsoft-windows-7)
    - [Remoção em *Microsoft Windows 10 ou 11*](#remoção-em-microsoft-windows-10-ou-11)
    - [Remoção em Linux](#remoção-em-linux)
      - [Remoção através do *Software Center*](#remoção-através-do-software-center)
      - [Remoção através da linha de comandos](#remoção-através-da-linha-de-comandos)
    - [Remoção em MacOS](#remoção-em-macos)
  - [Atualização da aplicação](#atualização-da-aplicação)
    - [Atualização em Linux](#atualização-em-linux)
- [Aplicação Utilitária “Autenticação.Gov”](#aplicação-utilitária-autenticaçãogov)
  - [Apresentação da Aplicação](#apresentação-da-aplicação)
  - [Funcionalidades da aplicação](#funcionalidades-da-aplicação)
    - [Menu Cartão](#menu-cartão)
      - [Identidade](#identidade)
      - [Outros dados](#outros-dados)
      - [Morada e Alteração de morada](#morada-e-alteração-de-morada)
      - [Notas](#notas)
      - [Imprimir](#imprimir)
    - [Assinatura digital](#assinatura-digital)
      - [Introdução de chave](#introdução-de-chave)
      - [Verificação de documento PDF assinado em Windows ou MacOS](#verificação-de-documento-pdf-assinado-em-windows-ou-macos)
    - [Segurança](#segurança)
      - [Certificados](#certificados)
      - [Código PIN](#código-pin)
    - [Configurações](#configurações)
      - [Personalização da Assinatura](#personalização-da-assinatura)
      - [Atributos Profissionais](#atributos-profissionais)
      - [Configuração da aplicação](#configuração-da-aplicação)
      - [Configuração de assinaturas](#configuração-de-assinaturas)
      - [Dados da aplicação](#dados-da-aplicação)
      - [Atualizações](#atualizações)
    - [Ajuda](#ajuda)
    - [Centro de Notificações](#centro-de-notificações)
- [Integração com aplicações](#integração-com-aplicações)
  - [Integração com aplicações em Windows](#integração-com-aplicações-em-windows)
  - [Integração com aplicações via interface PKCS\#11](#integração-com-aplicações-via-interface-pkcs11)
  - [Assinatura digital em aplicações comuns](#assinatura-digital-em-aplicações-comuns)
    - [Assinatura digital na suite *Microsoft Office*](#assinatura-digital-na-suite-microsoft-office)
    - [Assinatura digital na suite *LibreOffice / OpenOffice*](#assinatura-digital-na-suite-libreoffice--openoffice)
    - [Assinatura digital de email com *Microsoft Outlook*](#assinatura-digital-de-email-com-microsoft-outlook)
    - [Assinatura digital em *Adobe Acrobat Reader*](#assinatura-digital-em-adobe-acrobat-reader)
    - [Assinatura digital em *Adobe Acrobat Reader DC* em MacOS com PKCS#11](#assinatura-digital-em-adobe-acrobat-reader-dc-em-macos-com-pkcs11)
    - [Assinatura digital com Chave Móvel Digital](#assinatura-digital-com-chave-móvel-digital)
    - [Assinatura digital de email com Mozilla Thunderbird](#assinatura-digital-de-email-com-mozilla-thunderbird)
    - [Assinatura digital de ficheiros DWF](#assinatura-digital-de-ficheiros-dwf)
    - [Assinatura digital de ficheiros DWG](#assinatura-digital-de-ficheiros-dwg)
  - [Autenticação em portais WEB](#autenticação-em-portais-web)
    - [Configurar autenticação para *Mozilla Firefox*](#configurar-autenticação-para-mozilla-firefox)
- [Resolução de Problemas](#resolução-de-problemas)
  - [Exibida mensagem de erro quando se tenta adicionar o módulo PKCS\#11 no *Firefox / Thunderbird*](#exibida-mensagem-de-erro-quando-se-tenta-adicionar-o-módulo-pkcs11-no-firefox--thunderbird)
  - [Não é possível adicionar o módulo PKCS\#11 ao *Adobe Acrobat Reader* em *MacOS*](#não-é-possível-adicionar-o-módulo-pkcs11-ao-adobe-acrobat-reader-em-macos)
  - [Impossibilidade de assinatura em *Adobe Reader*, *Microsoft Office* e *LibreOffice* com Cartão de Cidadão](#impossibilidade-de-assinatura-em-adobe-reader-microsoft-office-e-libreoffice-com-cartão-de-cidadão)
  - [O leitor de cartões está instalado mas não é detetado pela aplicação do Cartão de Cidadão](#o-leitor-de-cartões-está-instalado-mas-não-é-detetado-pela-aplicação-do-cartão-de-cidadão)
  - [Não são detetados quaisquer certificados durante a tentativa de assinatura na suite *LibreOffice / Apache OpenOffice*](#não-são-detetados-quaisquer-certificados-durante-a-tentativa-de-assinatura-na-suite-libreoffice--apache-openoffice)
  - [Problemas gráficos na aplicação](#problemas-gráficos-na-aplicação)
  - [Problemas com placas gráficas integradas](#problemas-com-placas-gráficas-integradas)
  - [Não é possível mover/arrastar a aplicação (Linux em Wayland)](#não-é-possível-moverarrastar-a-aplicação-linux-em-wayland)
  - [Aplicação não arranca](#aplicação-não-arranca)
  - [Problemas com a nova cadeia de confiança](#problemas-com-a-nova-cadeia-de-confiança)
  - [Problemas na validação das assinaturas](#problemas-na-validação-das-assinaturas)
  - [Problemas com ficheiros PDF não suportados](#problemas-com-ficheiros-pdf-não-suportados)
  - [Serviços online usados pela aplicação](#serviços-online-usados-pela-aplicação)
  - [Obtenção do relatório para análise através do menu Configurações](#obtenção-do-relatório-para-análise-através-do-menu-configurações)
  - [Remoção de metadados de ficheiros PDF](#remoção-de-metadados-de-ficheiros-pdf)
- [Interface de linha de comandos](#interface-de-linha-de-comandos)
  - [Consultar ajuda e versão](#consultar-ajuda-e-versão)
  - [Atalho para configurar modo de renderização gráfica](#atalho-para-configurar-modo-de-renderização-gráfica)
  - [Atalho para submenu de assinatura](#atalho-para-submenu-de-assinatura)
- [Instruções de configuração em ambientes empresariais](#instruções-de-configuração-em-ambientes-empresariais)
  - [Configurações através de chaves de registo Windows](#configurações-através-de-chaves-de-registo-windows)
  - [Configurações através de ficheiro de configuração em Linux e MacOS](#configurações-através-de-ficheiro-de-configuração-em-linux-e-macos)
  - [Instalação automatizada em ambientes Windows](#instalação-automatizada-em-ambientes-windows)
  - [Informação sobre servidores de Proxy](#informação-sobre-servidores-de-proxy)

<!-- DO NOT REMOVE the next comment. It is here so the script to generate the pdf version of this manual knows where the content starts and to ignore the table of contents. -->
<!-- Content_begin -->

# Introdução

NOTA: A partir da versão 3.2.0, houve uma alteração de nomenclatura no manual. As referências a "Middleware do Cartão de Cidadão" foram substituídas por "aplicação Autenticação.gov para computador", excepto quando o termo "*middleware*" é mais apropriado.

Este manual pretende descrever todas as funcionalidades providenciadas pela aplicação Autenticação.gov para computador.

A instalação da aplicação Autenticação.gov para computador inclui o *middleware* do Cartão de Cidadão. Este *middleware* pode definir-se como a “camada” de
software entre o computador e o seu Cartão de Cidadão e é através deste que são disponibilizadas ao sistema operativo e outras aplicações funcionalidades de autenticação e assinatura eletrónica.

A aplicação Autenticação.gov para computador permite a gestão do seu
Cartão de Cidadão. Nesta poderá visualizar as suas informações, editar as
suas notas, modificar os seus *PIN*s pessoais e assinar digitalmente
ficheiros.

Este manual pode-se dividir nas seguintes fundamentais áreas de utilização:

-   Na primeira área (no capítulo dois), aborda o descarregamento,
    instalação e remoção da aplicação;
-   Na segunda área (no capítulo três), descreve as funcionalidades da
    aplicação de gestão do Cartão de Cidadão e de assinatura digital de
    ficheiros;
-   Na terceira área (nos capítulos quatro e cinco), documenta as
    integrações do *middleware* com o sistema operativo e aplicações;
-   Na quarta área (no capítulo seis), documenta a instalação
    automatizada em ambientes Windows e configuração em ambientes
    empresariais;

# Download, instalação e remoção da aplicação

Neste ponto são apresentadas as instruções para a instalação e remoção
da aplicação Autenticação.gov para computador.

## Sistemas Operativos oficialmente suportados

A lista de sistemas operativos suportados, nas suas arquiteturas de 32 e
64 bits, são:

-   Sistemas operativos Microsoft:
    -   Microsoft Windows 7
    -   Microsoft Windows 8
    -   Microsoft Windows 8.1
    -   Microsoft Windows 10
    -   Microsoft Windows 11
-   Distribuições de Linux suportadas:
    -   Fedora 28 e superiores
    -   OpenSuse Leap 15 e superiores
    -   Ubuntu 18.04 e superiores
-   Sistemas operativos Apple:
    -   Mac OSX Sierra (10.12) e superiores

## Download do pacote de instalação da aplicação

Para obter o pacote de instalação da aplicação, deverá aceder ao
sítio oficial do Cartão de Cidadão em
[http://www.autenticacao.gov.pt](http://www.autenticacao.gov.pt/) e pressionar "Descarregar" no rectângulo com título "Aplicação Autenticação.gov".

![Ilustração: Download](Pictures/Portal_Autenticacao.Gov_Homepage.png "Download"){:.center}

Ao clicar no link "aplicação Autenticação.gov para computador" será efetuada uma tentativa de identificar o sistema operativo através dos dados
fornecidos pelo navegador e na área de conteúdos do lado direito será
apresentada uma hiperligação que permite efetuar o descarregamento do
pacote de instalação adequado ao seu sistema.

Nos casos dos sistemas operativos *Linux*, não é possível identificar a
distribuição, deste modo será apresentada a lista das distribuições
suportadas para a arquitetura detetada.

Esta página contém a lista de todos os pacotes de instalação dos sistemas operativos oficialmente suportados e manuais de utilização.

![Ilustração: Opções de download](Pictures/Portal_Autenticacao.Gov_Download.png "Opções de download"){:.center}

Após efetuado o download da respetiva versão, avance
para o ponto seguinte – Instalação da aplicação.

## Instalação da aplicação

As instruções apresentadas de seguida pressupõem que o ficheiro de
instalação da aplicação foi descarregado previamente da Internet.
Caso não tenha sido, efetue os passos descritos no ponto anterior –
Download do pacote de instalação da aplicação.

Para a instalação da aplicação Autenticação.gov para computador, deverão ser
executados os passos descritos nos pontos seguintes, relativos ao
sistema operativo utilizado.

### Instalação em Microsoft Windows

1.  Executar o pacote de instalação: Após ter descarregado o ficheiro de
    instalação, deverá fazer duplo clique sobre este.

2.  No primeiro ecrã interativo, deverá marcar a caixa para aceitar os termos e condições da aplicação. Para uma instalação simples, pressione **Instalar** (prossiga para o passo 5). Para uma instalação avançada, pressione **Avançadas**. A instalação avançada permite configurar o caminho para a pasta de instalação e as funcionalidades a serem instaladas.

    ![Ilustração: Instalação da aplicação em Windows](Pictures/Autenticacao.Gov_Instalacao_termos.png "Instalação da aplicação em Windows"){:.center}

3.  Após selecionar **Avançadas**, poderá escolher a pasta onde deseja instalar a aplicação. Se desejar alterar a pasta predefinida, carregue em
    **Alterar** e na janela que surgir, navegue até à pasta de destino
    e carregue **OK**. Para continuar a instalação na pasta de destino,
    deverá premir o botão **Seguinte**.

4.  Deverá aparecer um ecrã para escolher as funcionalidades a serem instaladas. As funcionalidades são representadas numa árvore em que cada funcionalidade possui um botão para a incluir ou excluir da instalação. Ao selecionar uma funcionalidade, será apresentada a sua descrição e o espaço necessário para a sua instalação. Para instalar as funcionalidades selecionadas, clique em **Instalar**.

    ![Ilustração: Instalação da aplicação em Windows](Pictures/Autenticacao.Gov_Instalacao_funcionalidades.png "Instalação da aplicação em Windows"){:.center}

5.  Após a conclusão deste assistente, este solicitará a reinicialização
    do computador.

6.  No próximo arranque do *Windows* a instalação da aplicação estará
    finalizada.

### Instalação em Linux

Algumas distribuições de Linux, disponibilizam um gestor de aplicações -
“*Software Center*” - onde é possível instalar o software através de um
assistente gráfico. Este ecrã poderá variar consoante a distribuição,
neste manual apresenta-se as imagens da utilização do *Ubuntu Software*
em Linux Ubuntu 18.04.3 LTS.

Em alternativa, a aplicação poderá também ser [instalada recorrendo à linha de comandos](#instala%c3%a7%c3%a3o-atrav%c3%a9s-da-linha-de-comandos).

#### Instalação através do *Software Center*

1.  Executar o pacote de instalação: Após ter
    descarregado o ficheiro de instalação, deverá fazer duplo clique
    sobre este. O sistema deverá apresentar o ecrã de
    gestão de aplicações - “Software Center” - para a instalação do
    software. Este ecrã varia consoante a distribuição que está a
    utilizar, no entanto, as opções são semelhantes em todos. Nos ecrãs
    seguintes são apresentados os ecrãs utilizando Linux Ubuntu 18.04.3 LTS.

    ![Ilustração: Instalação da aplicação em Linux](Pictures/Autenticacao.Gov_Instalacao_Linux.png "Instalação da aplicação em Linux"){:.center}

2.  Deverá premir o botão **Instalar** para prosseguir.
3.  Será pedida a introdução da sua senha de utilizador. (É necessário
    que tenha privilégios de administração da máquina)

    ![Ilustração: Instalação_Linux](Pictures/Autenticacao.Gov_Instalacao_Linux3.png "Instalação_Linux"){:.center}

4.  Após a conclusão do passo acima, a instalação da aplicação está
    terminada.
5.  Recomenda-se a reinicialização do sistema para assegurar o bom
    funcionamento da aplicação.

#### Instalação através da linha de comandos

1.  Execute o comando de instalação de software no sistema, consoante o
    gestor de pacotes utilizado pelo seu sistema.

    Sistema baseado em pacotes .deb, execute o comando:

    `sudo apt install ./Autenticacao.gov*.deb`

    Por exemplo, em *Ubuntu 20*:

    `sudo apt install ./Autenticacao.gov_Ubuntu_20_x64`

    Sistema baseado em pacotes .rpm, execute o comando:

    `dnf install Autenticacao.gov*.rpm` em Fedora ou
    `zypper install Autenticacao.gov*.rpm` em OpenSUSE

    Por exemplo, em *Fedora*:

    `dnf install Autenticacao.gov_Fedora_x64.rpm`

    Por exemplo, em *OpenSUSE*:

    `zypper install Autenticacao.gov_OpenSUSE_x64.rpm`

2.  Após este passo, a aplicação terá sido instalada no computador.
3.  Recomenda-se a reinicialização do sistema para assegurar o bom
    funcionamento da aplicação.

### Instalação em Mac OS

1.  Executar o instalador: após ter descarregado o ficheiro de
    instalação, deverá fazer duplo clique sobre este, surgindo um ecrã
    semelhante ao apresentado de seguida:

    ![Ilustração: Instalação da aplicação em MacOS](Pictures/Autenticacao.Gov_Instalacao_MacOS.png "Instalação da aplicação em MacOS"){:.center}

2.  Escolher a opção **Continuar**. Em seguida é
    necessário ler e aceitar os termos da licença do software.

    ![Ilustração: Instalação da aplicação em MacOS](Pictures/Autenticacao.Gov_Instalacao_MacOS2.png "Instalação da aplicação em MacOS"){:.center}

3.  A partir deste ponto no assistente deverá premir o botão
    **Continuar** até concluir a instalação.
    
4.  Após a conclusão deste assistente, a aplicação estará instalada
    no computador. Neste momento a aplicação utilitária
    “Autenticacão.Gov” já estará disponível na pasta Aplicações /
    *Applications*.

## Remoção da aplicação

Para proceder à remoção da aplicação do Cartão de Cidadão, deverão
ser executados os passos descritos nos pontos seguintes, relativos ao
sistema operativo utilizado.

### Remoção em *Microsoft Windows 7*

1.  Aceda ao **Painel de Controlo**.

2.  Selecione a **Opção Adicionar ou Remover Programas**.

3.  Selecione o programa **Autenticação.Gov**, conforme apresentado na janela seguinte:
![Ilustração: Remoção da aplicação em Windows](Pictures/Autenticacao.Gov_Desinstalar.png "Remoção da aplicação em Windows"){:.center}

4.  Clique em **Desinstalar**. Confirme todas as janelas de diálogo que irão
    surgir.

5.  Após estes passos, a aplicação estará removida do computador.
    Recomenda-se que o computador seja reiniciado no final destes
    passos.

### Remoção em *Microsoft Windows 10 ou 11*
#### Através do Painel de Controlo <!-- omit in toc -->

1.  Aceda ao **Painel de Controlo**.

2.  Selecione a **Opção Adicionar ou Remover Programas**.

3.  Selecione o programa **Autenticação.Gov**, conforme apresentado na janela seguinte:
![Ilustração: Remoção da aplicação em Windows](Pictures/Autenticacao.Gov_Desinstalar_Win10.png "Remoção da aplicação em Windows 10 Painel Controlo"){:.center}

4.  Clique em **Desinstalar**. Confirme todas as janelas de diálogo que irão
    surgir.
5.  Após estes passos, a aplicação estará removida do computador.
    Recomenda-se que o computador seja reiniciado no final destes
    passos.

#### Através das Definições do *Windows* <!-- omit in toc -->

1.  Aceda a **Menu Iniciar** -> **Definições do Windows**.
2.  Prima o botão **Aplicações** (Desinstalar, predefinições, funcionalidades opcionais):

      ![Ilustração: Definições em Windows](Pictures/Definicoes_Windows_janela.png "Definições Windows 10"){:.center}

3.  Procure a aplicação **Autenticação.Gov**; selecione a aplicação e pressione **Desinstalar**, conforme apresentado na figura seguinte:

      ![Ilustração: Remoção da aplicação em Windows](Pictures/Autenticacao.Gov_Add_Remove_janela.png "Remoção da aplicação em Windows 10"){:.center}

4.  Clique em **Desinstalar**. Confirme todas as janelas de diálogo que irão
    surgir.
5.  Após estes passos, a aplicação estará removida do computador.
    Recomenda-se que o computador seja reiniciado no final destes
    passos.


**Nota**: a diretoria e ficheiros de *log*, que se encontram na pasta de instalação (por defeito em `C:\Program Files\Portugal Identity Card\log\`), não serão removidos ao desinstalar a aplicação Autenticação.Gov.

### Remoção em Linux

Algumas distribuições de Linux, disponibilizam um gestor de aplicações -
“**Software Center**” - onde é possível remover o software através de um
assistente gráfico. Este ecrã poderá variar consoante a distribuição,
neste manual apresenta-se as imagens da utilização do *Ubuntu Software*
em Linux Ubuntu 18.04.3 LTS.

Em alternativa, a aplicação poderá também ser [removida recorrendo à linha de comandos](#remo%c3%a7%c3%a3o-atrav%c3%a9s-da-linha-de-comandos).

#### Remoção através do *Software Center*

Este ecrã varia consoante a distribuição que está a utilizar, no
entanto, as opções são semelhantes em qualquer distribuição. Nos ecrãs
seguintes são apresentados os ecrãs utilizando Linux Ubuntu 18.04.3 LTS.

1.  Abra o **Ubuntu Software** (Menu Aplicações → Ubuntu Software).

    ![Ilustração: Remoção da aplicação em Linux](Pictures/Autenticacao.Gov_Desinstalar_Linux.png "Remoção da aplicação em Linux"){:.center}

2.  Prima o botão **Instalado** na barra superior.

    ![Ilustração: Remoção da aplicação em Linux](Pictures/Autenticacao.Gov_Desinstalar_Linux2.png "Remoção da aplicação em Linux"){:.center}

3.  Localize o pacote **pteid-mw** e prima o botão **Remover**.

    ![Ilustração: Remoção da aplicação em Linux](Pictures/Autenticacao.Gov_Desinstalar_Linux3.png "Remoção da aplicação em Linux"){:.center}

4.  Confirme a operação, voltando a escolher o botão **Remover**.

    ![Ilustração: Remoção da aplicação em Linux](Pictures/Autenticacao.Gov_Desinstalar_Linux4.png "Remoção da aplicação em Linux"){:.center}

5.  Será pedida a introdução da sua senha de utilizador. É necessário
    que tenha privilégios de administração da máquina.

    ![Ilustração: Apresentação da aplicação](Pictures/Autenticacao.Gov_Desinstalar_Linux5.png "Apresentação da aplicação"){:.center}

6.  Após a conclusão do passo acima a o processo de desinstalação da
    aplicação está terminado.

#### Remoção através da linha de comandos

1.  Execute o comando de remoção de software no sistema, consoante o
    gestor de pacotes utilizado pelo seu sistema.

    Gestor de pacotes baseado em ficheiros .deb, execute o comando:

    `sudo dpkg -r pteid-mw`

    Gestor de pacotes baseado em ficheiros .rpm, execute o comando:

    `sudo rpm -e pteid-mw`

2.  Após este passo, a aplicação terá sido removida do computador.

### Remoção em MacOS

1.  Abra a aplicação "**Terminal**" no MacOS.
2.  Execute o seguinte comando para desinstalar todos os ficheiros da aplicação.

    `sudo /usr/local/bin/pteid_uninstall.sh`

## Atualização da aplicação

### Atualização em Linux

Ao transferir o ficheiro de instalação através do menu **Configurações** -> **Atualizações** da aplicação, este ficará disponível na diretoria das **Transferências**, por defeito em `~/Downloads`.

Pode também transferir a nova versão da aplicação através do site [oficial](https://www.autenticacao.gov.pt/web/guest/cc-aplicacao).

Depois de ter transferido o ficheiro de instalação, para concluir a atualização:

* Através da linha de comandos: siga os passos indicados na secção [Instalação através da linha de comandos](#instalação-através-da-linha-de-comandos).

* Através do gestor de aplicações: siga os passos indicados na secção [Instalação através do *Software Center*](#instalação-através-do-software-center).

    Nota: Ao utilizar o gestor de aplicações para atualizar a aplicação pode ser necessário remover a aplicação primeiro e só depois instalar a nova versão.


# Aplicação Utilitária “Autenticação.Gov”

A aplicação utilitária “Autenticação.Gov” pode ser utilizada para
visualizar e gerir os dados no Cartão de Cidadão e assinar documentos
digitais.

![Ilustração: Apresentação da aplicação](Pictures/Autenticacao.Gov_Home.png "Apresentação da aplicação"){:.center}

Nesta aplicação poderá efetuar as seguintes operações:

-   Visualização da informação e foto do cidadão;
-   Visualização da morada do cidadão e confirmação da alteração de
    morada;
-   Edição das notas;
-   Imprimir os dados do Cartão de Cidadão;
-   Assinatura digital de documentos PDF e outros ficheiros;
-   Visualização dos certificados do Estado e do cidadão;
-   Registo dos certificados do Estado e do cidadão (específico de
    Microsoft Windows);
-   Gestão de PINs (Testar PIN, Alterar PIN).

O aspeto e comportamentos da aplicação é semelhante nos três tipos de
sistemas operativos, à exceção de algumas funcionalidades que estão apenas disponíveis em Windows, visto serem
funcionalidades nativas ou configurações específicas do ambiente Windows.

O atalho para a aplicação fica disponível em localizações diferentes
consoante o tipo de sistema operativo:

-   Em Windows surgirá em: **Iniciar** → **Programas** → **Autenticação.Gov**
-   Em Linux surgirá em: **Aplicações** → **Acessórios** → **Autenticação.Gov**
-   Em MacOS, surgirá na localização escolhida pelo utilizador durante
    o processo de instalação.

## Apresentação da Aplicação

A aplicação é composta por 4 áreas principais de interação:

-   **Menu principal:** São disponibilizadas as três funcionalidade
    básicas da aplicação;
-   **Menu secundário:** São disponibilizadas as funcionalidades
    específicas de cada opção do menu principal;
-   **Menu configurações e ajuda:** São disponibilizados os menus de
    configuração e ajuda;
-   **Área de trabalho:** Área de visualização de dados
    do Cartão de Cidadão e área de trabalho para os menus de assinatura
    e segurança.

![Ilustração: Áreas principais de interação da aplicação](Pictures/Autenticacao.Gov_Card.png "Áreas principais de interação da aplicação"){:.center}

## Funcionalidades da aplicação

As funcionalidades da aplicação estão divididas, em três menus
principais: Menu Cartão, Menu Assinatura e Menu Segurança.

### Menu Cartão

Permite visualizar a informação de identidade e foto do cidadão,
visualizar a morada do cidadão e confirmar a alteração de morada, edição
das notas, bem como imprimir os dados do Cartão de Cidadão. A foto do cidadão
pode ser exportada para um ficheiro.

#### Identidade

Permite visualizar os dados de identificação e foto do cidadão presentes
no Cartão de Cidadão. Através desta página é ainda possível exportar foto do cidadão.

![Ilustração: Identidade do Cidadão](Pictures/Autenticacao.Gov_Identidade.png "Identidade do Cidadão"){:.center}

#### Outros dados

Permite visualizar outros dados do cartão do cidadão e verificar o
estado do Cartão de Cidadão.

![Ilustração: Outros dados](Pictures/Autenticacao.Gov_Outros_Dados.png "Outros dados"){:.center}

#### Morada e Alteração de morada

Dentro do separador “Morada” é possível visualizar a morada atual e
completar o processo de alteração da morada. Esta última funcionalidade
requer uma ligação à Internet. Por favor certifique-se que está ligado
antes de iniciar o processo.

**Nota:** Durante o processo de alteração de morada, o cidadão terá de
se autenticar, por isso deverá ter presente e desbloqueado o seu PIN de
autenticação.

Para concluir um eventual processo de alteração de morada, siga os
seguintes passos:

1.  Clique no botão **Confirmar**.

    ![Ilustração: Introduzir dados na confirmar de alteração de morada    ](Pictures/Autenticacao.Gov_Morada.png "Introduzir dados na confirmar de alteração de morada"){:.center}

2.  Caso tenha solicitado uma alteração de morada, insira o número de processo e o código secreto recebidos pelo correio.

    ![Ilustração: Confirmar alteração de morada](Pictures/Autenticacao.Gov_morada2.png "Confirmar alteração de morada"){:.center}

3.  Aguarde o processamento do pedido. Será efetuada uma ligação ao
    serviço de alteração de morada e será pedido o seu PIN de
    autenticação para obter os dados da nova morada e em seguida a nova
    morada será escrita no Cartão de Cidadão.

    **Por favor, não retire o cartão do leitor, não desligue a aplicação
    e não desligue a Internet, enquanto o processo não estiver
    terminado.**

    ![Ilustração: Pedido de confirmação em processamento](Pictures/Autenticacao.Gov_morada3.png "Pedido de confirmação em processamento"){:.center}

4.  Verifique o resultado da operação e siga as instruções:

    - **A morada foi atualizada com sucesso:**

      Caso a operação tenha sucesso, uma mensagem de Confirmação de alteração de morada com sucesso será mostrada.

        ![Ilustração: Sucesso na alteração de morada](Pictures/Autenticacao.Gov_Alteracao_morada_sucesso.png "Sucesso na alteração de morada"){:.center}

    - **Erro. Ocorreu um erro durante a alteração de morada:**

      Caso a aplicação não consiga efetuar a confirmação da alteração de morada será mostrada uma mensagem de erro e respetivo código, que deverá anotar.

      Nas seguintes mensagens de erro:

      - **Não existe uma alteração de morada registada para este cartão.**

        Caso tenha solicitado uma alteração de morada, verifique que está a usar o Cartão de Cidadão correto.

        ![Ilustração: Erro na alteração de morada](Pictures/Autenticacao.Gov_morada4.png "Erro na alteração de morada"){:.center}

      - **Processo de alteração de morada não foi concluído. A morada foi alterada no cartão mas não foi confirmada pelos serviços centrais.**

        Caso a aplicação não consiga efetuar a confirmação da alteração de morada será mostrada uma mensagem de erro, que deverá anotar. Deverá aguardar cerca de 5 minutos e tentar novamente e, caso o erro persista, contactar a Linha Cartão de Cidadão através do número 211 950 500 ou do E-mail: cartaodecidadao@irn.mj.pt . (Tenha consigo as mensagens de erro e o número de processo de Alteração de Morada)

        ![Ilustração: Erro na alteração de morada](Pictures/Autenticacao.Gov_Alteracao_morada_erro_n_confirmada.png "Erro na alteração de morada"){:.center}

      - **Este erro pode ser resultado da interferência na rede, por um software externo à aplicação Autenticação.Gov.**

        A alteração de morada requer a ligação a um serviço online para funcionar corretamente. É por isso necessário garantir que não existe firewall ou outro software na rede local que impeça a ligação a este serviço. Garanta que o seguinte serviço não é bloqueado:
        * pki.cartaodecidadao.pt (porto 443) 

        No caso de estar ligado a uma rede empresarial, contacte o seu administrador de rede e peça uma configuração de rede que resolva o problema.
        
        ![Ilustração: Erro na alteração de morada](Pictures/Autenticacao.Gov_morada5.png "Pedido de confirmação em processamento"){:.center}
      - **O prazo de confirmação da alteração de morada expirou. Por favor efetue um novo pedido através dos balcões de atendimento ou do portal ePortugal.**

        Neste caso deverá efectuar um novo pedido através dos balcões de atendimento ou do portal ePortugal.

      Nas seguintes mensagens de erro:

      - **Erro na comunicação.**

      - **Verifique se introduziu corretamente o número de processo e código de confirmação.**

      - **Verifique que tem o certificado de autenticação válido.**

      - **Verifique que tem a última versão da aplicação.**

        E em outras mensagens, sem descrição do erro, deverá tentar novamente seguindo as instruções e, caso o erro persista, contactar a Linha Cartão de Cidadão através do  número **211 950 500** ou do E-mail: **cartaodecidadao@irn.mj.pt**. (Tenha consigo as mensagens de erro e respectivos códigos de erro e o número de processo de Alteração de Morada).

        A imagem seguinte é um exemplo da mensagem de erro.

        ![Ilustração: Erro na alteração de morada](Pictures/Autenticacao.Gov_Alteracao_morada_erro.png "Erro na alteração de morada"){:.center}

#### Notas

A aplicação permite editar as notas gravadas no cartão do cidadão.

A leitura desta informação não requer qualquer código. Pode, por
exemplo, inserir informação sobre contactos em caso de urgência,
indicações sobre alergias, medicação, grupo sanguíneo ou outra qualquer
informação que entenda pertinente e de acesso livre.

![Ilustração: Editar notas](Pictures/Autenticacao.Gov_Notas.png "Editar notas"){:.center}

#### Imprimir

A aplicação permite a exportação dos dados do Cartão de Cidadão para um
documento no formato PDF ou a impressão direta do documento.

Para executar estas operações deverá executar os seguintes passos:

1.  No menu principal, selecionar **Cartão** e no menu secundário a opção
    **Imprimir**.
2.  Na área de trabalho deverá selecionar os grupos de campos a incluir
    no documento e selecionar a opção **Imprimir** ou **Gerar PDF**.

    ![Ilustração: Opções de exportação de PDF ou impressão](Pictures/Autenticacao.Gov_Print.png "Opções de exportação de PDF ou impressão"){:.center}

    O documento a ser exportado e/ou impresso terá um aspeto gráfico
    conforme a imagem seguinte.

    ![Ilustração: Exemplo de exportação de PDF](Pictures/Autenticacao.Gov_print_pdf.png "Exemplo de exportação de PDF]"){:.center}

### Assinatura digital

A assinatura digital permite ao titular de um **Cartão de Cidadão** ou da **Chave Móvel Digital**,
por vontade própria, assinar com a chave pessoal existente no seu Cartão de Cidadão ou com a Chave
Móvel Digital.

**No menu Assinatura** pode assinar um documento PDF ou outro qualquer documento com possibilidade
de assinar vários documentos ao mesmo tempo, adicionar atributos profissionais, bem como configurar
outras opções. A assinatura digital em documentos PDF foi desenvolvida de acordo com a especificação
da Adobe, podendo assim ser validada posteriormente no software *Adobe Reader*.

![Ilustração: Menu de assinatura digital](Pictures/Autenticacao.Gov_assinatura.png "Menu de assinatura digital"){:.center}

Os ficheiros a assinar podem ser selecionados arrastando-os para a área de pré-visualização ou
utilizando a combinação de teclas **CTRL+V** (colar). Pode também clicar na área de pré-visualização
ou no botão **Adicionar ficheiros** e selecionar manualmente os ficheiros. Será exibida uma janela
para selecionar os ficheiros que pretende assinar. Os ficheiros selecionados serão apresentados na
secção “Escolha os ficheiros”, como podemos visualizar na imagem seguinte.

![Ilustração: Submemu de assinatura avançada](Pictures/Autenticacao.Gov_assinatura2.png "Menu de assinatura digital 2"){:.center}

- **Escolha os ficheiros:**
  - O botão **Adicionar ficheiros** Abre uma nova janela que permitirá selecionar os documentos a
    serem assinados.
  - É possível remover ficheiros individualmente, utilizando o botão que se encontra junto ao nome
    de cada ficheiro, ou todos, pressionando o botão **Remover todos**.

- **Pré-visualização da assinatura:** Permite visualizar o documento a ser assinado e ajustar a
  posição e o tamanho do selo de assinatura, movendo-o para o local pretendido com as dimensões desejadas. A pré-visualização existe apenas
  para assinatura do tipo **PDF**, sendo possível pré-visualizar qualquer um dos ficheiros de uma multi-assinatura através de um clique no nome do ficheiro na aplicação.

- **Opções avançadas - Configurações:**

    Após a seleção dos ficheiros, deverá selecionar as opções da assinatura. As configurações da
    assinatura são as seguintes:

    - **Tipo:** Tipo de assinatura – campo obrigatório – permite selecionar assinatura de ficheiros:

        - **PDF:** PAdES (*PDF Advanced Electronic Signatures*).
        - **Outros ficheiros:** Pacote ASiC com *XML Advanced Electronic Signatures* (XAdES).
          Este pacote é um arquivo ZIP criado de acordo com a especificação ASiC (Associated
          Signature Container) desenvolvido pela ETSI (European Telecommunications Standards
          Institute) e segue os padrões da UE. Este arquivo contém a informação assinada (ficheiros
          originais) e a respetiva assinatura no formato XAdES.

    - **Motivo:** Motivo da assinatura – campo opcional – permite ao signatário indicar o motivo da
      sua assinatura. Disponível para assinaturas do tipo **PDF**.

    - **Localização:** Local onde a assinatura foi efetuada – campo opcional - permite ao signatário
      indicar o local onde esta assinatura foi efetuada. Disponível para assinaturas do tipo
      **PDF**.

    - **Adicionar selo temporal:** Adiciona um selo temporal, provando a data à qual a assinatura
      foi efetuada. Esta é a única forma de provar que o documento existia a determinada hora, pois
      é aplicada ao documento a data e hora que este está a ser assinado, de forma segura. Note-se
      que a hora apresentada no selo visível é a hora local do computador onde foi efetuada a
      assinatura e pode não coincidir com a hora do selo temporal (obtida a partir de um servidor
      remoto). Disponível para assinaturas do tipo **PDF** e **Outros Ficheiros**. [Ver tópico
      Serviço de Selos Temporais na página Configuração de
      assinaturas](#configuração-de-assinaturas)

      - **Validação de longo prazo:** Com a opção ativa, os dados necessários
        para validar a assinatura digital serão incluídos no ficheiro final assinado. Deste modo, é
        possível provar no futuro que no momento da assinatura o certificado do cartão (ou Chave
        Móvel Digital) e respetiva cadeia não estavam revogados ou expirados. Este nível é
        recomendado para documentos que estão destinados a serem arquivados por um longo período de
        tempo. A assinatura com validação de longo prazo obedece à especificação do perfil PAdES-LTA.

        **Nota**: Disponível a partir da versão 3.4.0 da aplicação.

    - **Adicionar atributos profissionais:** A funcionalidade de assinatura de profissionais permite
      ao cidadão autenticar-se na  qualidade das funções que desempenha na sociedade enquanto
      profissional qualificado. Na secção [Atributos Profissionais](#atributos-profissionais) é
      indicado o procedimento para carregar os atributos profissionais. Disponível para assinaturas
      do tipo **PDF**.
    
        Para mais informações sobre atributos profissionais, consulte o seguinte *website*:
        
        <https://www.autenticacao.gov.pt/a-autenticacao-de-profissionais>

    - **Visível:** Permite que a assinatura fique visível no documento PDF. Disponível para
      assinaturas do tipo **PDF**.

    - **Página:** Poderá escolher a página onde será apresentada a assinatura. Disponível para
      assinaturas do tipo **PDF**.

    - **Última:** Poderá escolher a última página para apresentar a assinatura. Disponível para
      assinaturas do tipo **PDF**.

    - **Reduzida:** Permite utilizar um selo de assinatura de tamanho reduzido. O selo reduzido
      apresenta, sempre pela mesma ordem, os campos: "Nome Completo", "NIC" e "Data de assinatura",
      excepto se o espaço não for suficiente (nomes completos longos). Nesse caso são colocados
      apenas campos até preencher o espaço, pela mesma ordem. Disponível para assinaturas do tipo
      **PDF**.

Após selecionar as opções pretendidas, na área indicada na figura anterior, arraste a
pré-visualização do selo de assinatura para a localização pretendida e de seguida prima o botão
**Assinar com Cartão de Cidadão** ou **Assinar com Chave Móvel Digital**.

O botão **Assinar com Cartão de Cidadão** só está disponível quando o Cartão de Cidadão estiver
inserido no leitor de cartões e for corretamente lido pela aplicação.

Após clicar em **Assinar** deverá escolher a localização da pasta e do ficheiro onde guardar o
ficheiro assinado (Não é possível substituir o ficheiro original) e seguir o procedimento de
assinatura (ver secção [Introdução de chave](#introdu%c3%a7%c3%a3o-de-chave)). Em seguida é
apresentado uma mensagem a indicar que a assinatura digital foi efetuada com sucesso.

![Ilustração: Assinatura digital foi efetuada com sucesso](Pictures/Autenticacao.Gov_assinatura_sucesso.png "Assinatura digital foi efetuada com sucesso"){:.center}

A imagem seguinte é um exemplo de um ficheiro assinado com a aplicação **Autenticação.Gov**.

![Ilustração: Aspeto final da assinatura](Pictures/Autenticacao.Gov_Assinatura_exemplo.png "Aspeto final da assinatura"){:.center}

Em caso de problemas na validação das assinaturas, verifique se está relacionado com o tópico
[Problemas com a nova cadeia de confiança](#problemas-com-a-nova-cadeia-de-confiança).

#### Introdução de chave

A assinatura digital permite ao titular de um **Cartão de Cidadão** ou
da **Chave Móvel Digital**, por vontade própria, assinar com a chave
pessoal existente no seu Cartão de Cidadão ou com a Chave Móvel Digital.

No caso de pretender assinar com a chave pessoal existente no seu
Cartão de Cidadão, ao selecionar a opção **Assinar com Cartão de Cidadão**, deverá introduzir o PIN de assinatura, conforme a figura
seguinte.

![Ilustração: Assinatura digital com a chave pessoal do Cartão de Cidadão](Pictures/Autenticacao.Gov_assinatura_cc.png "Assinatura digital com a chave pessoal do Cartão de Cidadão"){:.center}

No caso, de pretender assinar com a Chave Móvel
Digital, ao selecionar a opção **Assinar com Chave Móvel Digital**, deverá
introduzir as respetivas credenciais, conforme a figura seguinte.

![Ilustração: Assinatura digital com a Chave Móvel Digital](Pictures/Autenticacao.Gov_assinatura_cmd.png "Assinatura digital com a Chave Móvel Digital"){:.center}

#### Verificação de documento PDF assinado em Windows ou MacOS

Após aplicar uma assinatura digital num documento, esta deverá ser
identificada automaticamente ao abrir o documento em *Adobe Reader*. A
imagem seguinte ilustra o *Adobe Reader* com um documento PDF que inclui
a assinatura efetuada no passo anterior:

![Ilustração: Assinatura de exemplo](Pictures/Autenticacao.Gov_assinatura_sample.png "Assinatura de exemplo"){:.center}

Mesmo quando a assinatura não esteja visível (se a opção "Visível" não for selecionada no
momento da assinatura), a assinatura deverá ser sempre validada no
painel de assinaturas, dado que permite a visualização do estado da
assinatura tendo em conta a cadeia de confiança e as propriedades
criptográficas da mesma.

![Ilustração: Validação da assinatura digital](Pictures/Autenticacao.Gov_assinatura_verify.png "Validação da assinatura digital"){:.center}

### Segurança

A aplicação permite efetuar operações relativas à segurança do Cartão de
Cidadão.

#### Certificados

Neste menu é possível verificar os certificados do Cidadão e a cadeia de confiança formada pelas várias Entidades de Certificação do Cartão de Cidadão e do Estado Português.

![Ilustração: Visualização do certificados](Pictures/Autenticacao.Gov_certificados_vazio.png "Visualização do certificados"){:.center}

O preenchimento dos campos "Estado do certificado" correspondem a uma validação junto da Infraestrutura do Cartão de Cidadão e como tal exige ligação à Internet. Esta validação é acionada ao pressionar o botão *Validar Certificados*.

![Ilustração: Visualização do certificados](Pictures/Autenticacao.Gov_certificados_validar.png "Visualização do certificados"){:.center}

É possível consultar a cadeia de certificados e os detalhes de cada certificado pressionando o botão *Ver detalhes*, visível na imagem anterior.

![Ilustração: Visualização do certificados - detalhes](Pictures/Autenticacao.Gov_certificados.png "Visualização do certificados - detalhes"){:.center}

#### Código PIN

Neste menu é possível verificar e alterar os códigos PIN do Cartão de
Cidadão.

-   **PIN de Autenticação:** Este PIN é usado para se autenticar em
    sites e aplicações que suportem o Cartão de Cidadão.
-   **PIN de Assinatura:** Este PIN é usado para assinar documentos ou
    transações em aplicações que suportem o Cartão de Cidadão.
-   **PIN de Morada:** Este PIN é usado para alteração e leitura de
    morada.

    ![Ilustração: Verificar e modificar códigos PIN](Pictures/Autenticacao.Gov_codigos_pin.png "Verificar e modificar códigos PIN"){:.center}

    ![Ilustração: Janela para modificar códigos PIN](Pictures/Autenticacao.Gov_Mudar_Pin.png "Janela para modificar códigos"){:.center}

### Configurações

#### Personalização da Assinatura

Neste menu é possível personalizar a assinatura digital, substituindo a
imagem do cartão do cidadão por uma imagem à escolha do utilizador.

O botão **Adicionar assinatura** permite selecionar uma imagem que será utilizada na assinatura personalizada. Após adicionar uma imagem, esta página da aplicação permitirá selecionar a opção **Usar assinatura padrão** ou **Usar assinatura personalizada**, conforme a escolha do utilizador.

O tamanho mínimo recomendado para a imagem é de 351 x 77px.

É, também, possível escolher incluir, ou não, a data de assinatura e o número de identificação civil na assinatura. Para tal, basta selecionar as checkboxes pretendidas neste menu. 

![Ilustração: Personalização da Assinatura digital](Pictures/Autenticacao.Gov_personalizar_assinatura.png "Personalização da Assinatura digital"){:.center}

#### Atributos Profissionais

O Sistema de Certificação de Atributos Profissionais (SCAP) permite ao cidadão, através do Cartão de Cidadão ou da Chave Móvel Digital, assinar um documento na qualidade das funções que desempenha enquanto profissional.
Para mais informações, consulte o seguinte *website*:

<https://www.autenticacao.gov.pt/a-autenticacao-de-profissionais>

Em primeiro lugar, selecione o tipo de atributos:

-   **No caso dos “Atributos Profissionais”** selecione a(s) entidade(s) fornecedora(s) dos seus atributos profissionais que pretende carregar atributos e clique em **Carregar atributos**.
-   **No caso dos “Atributos Empresariais”** os atributos do utilizador são carregados automaticamente, quando clicar no botão de carregamento de atributos.

    ![Ilustração: Carregar atributos profissionais](Pictures/Autenticacao.Gov_scap.png     "Carregar atributos profissionais"){:.center}

Seguidamente, deverá clicar num dos botões de carregamento de atributos, consoante pretenda, respetivamente, carregar os atributos com o Cartão de Cidadão ou Chave Móvel Digital (CMD):

-   **Carregar com Cartão de Cidadão**: após selecionar este botão, deverá
    introduzir o PIN de autenticação e aguardar pelo carregamento de
    atributos.
-   **Carregar com Chave Móvel Digital**: ao selecionar este botão será
    iniciado um processo de autenticação no seu navegador web
    predefinido (por exemplo, Google Chrome, Mozilla Firefox, Safari ou
    outro). Deverá carregar em **Autorizar** e preencher o formulário com
    número de telemóvel associado à sua Chave Móvel Digital e respetivo
    PIN. De seguida, carregue **Autenticar** e introduza o código de
    segurança que recebeu por SMS no telemóvel. Pressione **Confirmar**
    para completar a autenticação.

    Se concluiu a autenticação com sucesso, pode regressar à aplicação e
    aguardar pelo carregamento de atributos.

    ![Ilustração: Página de autenticação com Chave Móvel Digital.](Pictures/Autenticacao.Gov_cmd.png "Página de autenticação com Chave Móvel Digital."){:.center}

#### Configuração da aplicação

Nesta janela é possível configurar alguns aspetos do funcionamento da
aplicação, nomeadamente:

- **Leitor de Cartões:** Permite selecionar o leitor de cartões a utilizar.
  Existe também uma opção que permite ativar ou desativar a funcionalidade PINPAD (Os leitores com PINPAD são os leitores de cartões que possuem teclado para introdução segura do código PIN) nos leitores. Se esta opção estiver desativada, os leitores com PINPAD terão comportamento idêntico aos leitores sem PINPAD.

- **Início:** Opções relativas ao arranque da aplicação.

- **Atualizações automáticas**: Permite ativar ou desativar a verificação de
novas atualizações quando se inicia a aplicação. Esta opção apenas ativa ou desativa a verificação de novas versões da aplicação e não tem qualquer influência na atualização dos certificados, esta é feita automaticamente, sempre que necessário, aquando do início da aplicação. 

- **Idioma:** Selecionar o idioma da aplicação.

- **Aparência:** Opções relativas à aparência da aplicação. Nesta secção há uma opção que permite ativar e desativar as animações de transição entre menus.

- **Escala da aplicação:** Opções relativas ao tamanho do texto e outros componentes da aplicação.

- **Modo diagnóstico**: Permite ativar ou desativar o modo de diagnóstico da
aplicação. Este modo eleva o nível de detalhe do *log* para *debug*, o
que, em caso de problemas com a aplicação, pode ajudar a equipa de
suporte na resolução do problema.

  **A partir da versão 3.5.0 da aplicação, para todos os sistemas operativos, para ajudar a obter os ficheiros de log, existe uma nova funcionalidade que permite criar um "Relatório de Suporte". Para isso só é necessário pressionar o botão "Criar relatório".** Ao pressionar o botão **Criar relatório** é criada uma pasta comprimida (ficheiro zip) no Ambiente de Trabalho que contém todos os ficheiros de log. Deve enviar esta pasta (zip), anexada à descrição do problema, para o contacto de suporte. Para mais informações consulte o capítulo [Obtenção do relatório para análise através do menu Configurações](#obtenção-do-relatório-para-análise-através-do-menu-configurações).

  Os ficheiros de *log* por omissão são criados nas seguintes localizações e têm
  como nome o prefixo .PTEID:

    - **Windows**: Na pasta `log`, que se encontra na pasta de instalação (que por defeito é `C:\Program Files\Portugal Identity Card`), i.e.:

        `C:\Program Files\Portugal Identity Card\log\`

    - **MacOS**: Directoria Home do utilizador, i.e.:

        `/Users/Utilizador/`

    - **Linux**: Directoria Home do utilizador, i.e.:

        `/home/Utilizador/`

    **No caso da aplicação não arrancar**, é possível ativar o modo de diagnóstico usando as configurações do software Autenticação.gov via chaves de Registo em Windows ou ficheiro de configuração em Linux e MacOS.

    - Em **Windows**, a chave de registo:  
**HKEY\_CURRENT\_USER\\Software\\PTEID\\logging\\log_level**;

    - Em **Linux**, a chave com nome "log_level" na secção logging do ficheiro:  
**$HOME/.config/pteid.conf**;

    - Em **MacOS**, a chave com nome "log_level" na secção logging do ficheiro:  
**$HOME/Library/Preferences/pteid.conf**.

    Os valores que a chave pode tomar são:
    - debug (em caso de problemas com a aplicação, pode ajudar a equipa de suporte na resolução do problema)
    - info 
    - warning 
    - error (valor por omissão)

    Em ambiente empresariais deve alterar a seguinte configuração conforme descrito no capítulo [Instruções de configuração em ambientes empresariais](#instruções-de-configuração-em-ambientes-empresariais).

- **Aceleração gráfica**: Permite ativar escolher o modo de renderização gráfica da
aplicação. A opção *"Hardware (Placa gráfica)"* deverá oferecer um melhor desempenho, mas em caso de problemas poderá ser necessário configurar uma opção alternativa (ver secção [Problemas gráficos na aplicação](#problemas-gráficos-na-aplicação).)

- **Configurações de rede:** Opções relativas à configuração de servidor
de proxy. Em redes onde o acesso à Internet só é possível através de
servidor de *proxy* HTTP/S será necessário configurar as seguintes
informações de acesso:
    -   **Proxy de sistema (Windows e MacOS)**. Ao selecionar esta opção
        e se estiver definida uma configuração de *proxy* de sistema ou
        um *script* de auto configuração (*Proxy Autoconfig*), esta
        configuração será automaticamente utilizada pela aplicação e por outras aplicações que usem o *middleware* do cartão de cidadão.
    -   **Servidor proxy:** Endereço IP / Hostname / Porto.
    -   **Autenticação proxy:** Credenciais de acesso (se necessário).

A imagem seguinte permite visualizar o menu de configurações da aplicação.

![Ilustração: Janela de configurações da aplicação (com Modo de diagnóstico ativo)](Pictures/Autenticacao.Gov_configuracao.png "Janela de configurações da aplicação (com Modo de diagnóstico ativo)"){:.center}

#### Configuração de assinaturas

Nesta janela é possível configurar alguns aspetos relativos à assinatura
com o Cartão de Cidadão:

- **Certificados**: Opções relativas ao registo e remoção de certificados
durante a inserção e remoção do cartão.

    As opções de registo e remoção de certificados requerem que esta aplicação esteja em funcionamento (minimizada ou maximizada).

- **Serviço de Selos Temporais:** Configurar um serviço de selos temporais
personalizado.

    A aplicação permite seleccionar uma servidor diferente para a obtenção de
    selos temporais, uma vez que o servidor por defeito do Cartão do Cidadão
    ([http://ts.cartaodecidadao.pt/tsa/server](http://ts.cartaodecidadao.pt/tsa/server))
    tem um limite máximo de 20 pedidos em cada período de 20 minutos que
    se podem efectuar. Se este valor for excedido o serviço será bloqueado durante 24 horas, 
    sem prejuízo de outras consequências em caso de repetição de situações de bloqueio. 
    (para mais informações sobre o serviço de selo temporal/timestamps
    do Cartão do Cidadão, consulte a página
    [https://pki.cartaodecidadao.pt](https://pki.cartaodecidadao.pt)).

    Para usar um servidor diferente basta introduzir o url do servidor na caixa de texto.

    Após esta configuração tanto as assinaturas de documentos PDF (PAdES)
    bem como a assinaturas em formato XAdES vão usar este novo servidor
    configurado para obter os selos temporais ao assinar.


- **Microsoft Office (Windows):** Configurações relativas a assinaturas em
aplicações do Microsoft Office.

- **Chave Móvel Digital (Windows):** Permite registar o certificado associado à sua conta da Chave Móvel Digital. Para saber mais consulte a secção [Assinatura digital com Chave Móvel Digital](#assinatura-digital-com-chave-m%c3%b3vel-digital).

- **Novo certificado raiz do Estado (Windows):** Permite instalar o certificado raiz da nova cadeia de certificados do cartão de cidadão na *Store* de certificados raiz confiáveis do Windows. Para saber mais consulte a secção [Problemas com a nova cadeia de confiança](#problemas-com-a-nova-cadeia-de-confiança).

    A imagem seguinte permite visualizar o menu de configurações de assinaturas.

    ![Ilustração: Janela de configurações de assinaturas](Pictures/Autenticacao.Gov_configuracao_assinaturas.png "Janela de configurações de assinaturas"){:.center}

#### Dados da aplicação

Neste separador é possível apagar os dados de cache armazenados das
leituras dos cartões e do carregamento de atributos profissionais e
empresariais.

Os dados armazenados relativos ao Cartão de Cidadão são os dados de identidade, a foto e a cadeia de certificação de autenticação e assinatura.

Relativamente aos atributos profissionais e empresariais são armazenados a lista de atributos pré-carregados.

Existe também a possibilidade de desabilitar a cache dos dados dos cartões, desativando a opção "Utilizar cache de dados do cartão".

![Ilustração: Janela Dados Aplicação](Pictures/Autenticacao.Gov_dados_app.png "Janela Dados Aplicação"){:.center}

#### Atualizações

Nesta janela é possível verificar manualmente se existem atualizações
para a aplicação. Caso existam atualizações, e se o utilizador o
pretender, o download do instalador da aplicação é feito
automaticamente e em seguida iniciado o processo de instalação.

### Ajuda

A janela ajuda fornece um resumo das funcionalidades da aplicação, indica o caminho para chegar a este mesmo manual e a página de suporte da aplicação.

![Ilustração: Janela Acerca](Pictures/Autenticacao.Gov_acerca.png "Janela Acerca"){:.center}

### Centro de Notificações

Este menu disponibiliza aos utilizadores informações relativas a notícias, configurações e atualizações, no formato de notificações, e é aberto automaticamente no arranque da aplicação, caso existam notificações novas para mostrar ao utilizador.
![Ilustração: Centro de Notificações](Pictures/Autenticacao.Gov_centro_notificacoes.png "Centro de Notificações"){:.center}

No que diz respeito às configurações, podem existir notificações de caráter obrigatório cuja interação é necessária para utilizar a aplicação, como é o caso do uso da cache:

![Ilustração: Centro de Notificações - Cache](Pictures/Autenticacao.Gov_centro_notificacoes_cache.png "Centro de Notificações - Cache"){:.center}


# Integração com aplicações

O *middleware* do Cartão de Cidadão, instalado com a aplicação Autenticação.Gov, permite a integração com outras aplicações do sistema operativo, disponibilizando duas funcionalidades: Autenticação e Assinatura Digital.

O *middleware* disponibiliza suporte criptográfico às aplicações via suporte criptográfico nativo do sistema operativo Windows ou via interface PKCS\#11.



## Integração com aplicações em Windows

**Integração com aplicações com o Cartão de Cidadão:**

A instalação do *middleware* em Windows permite que, ao introduzir um Cartão de Cidadão no leitor, os certificados deste fiquem automaticamente registados no sistema operativo, ficando assim as funcionalidades de autenticação e assinatura disponíveis às aplicações que utilizam a camada criptográfica do sistema operativo, para operações de autenticação e assinatura.

Alguns exemplos dessas aplicações são: *Microsoft Word*, *Microsoft Excel*, *Microsoft Outlook* e *Adobe Acrobat Reader*. 

Para que os certificados fiquem automaticamente registados é necessário:

- A opção "Registar certificados com a inserção do cartão" disponível no separador [Configuração de assinaturas](#configura%c3%a7%c3%a3o-de-assinaturas) estar ativa.
- Abrir a aplicação local Autenticação.Gov antes de inserir o cartão. É aconselhado fazer pelo menos uma leitura do cartão na aplicação.

**Integração com aplicações com a Chave Móvel Digital:**

A integração com aplicações é também possível com a Chave Móvel Digital, ao nível da componente de assinatura digital. Para tal, siga o procedimento descrito em [Assinatura digital com Chave Móvel Digital](#assinatura-digital-com-chave-m%c3%b3vel-digital).

Na assinatura com Chave Móvel Digital, a mensagem de verificação enviada ao utilizador segue um dos 2 seguintes formatos:

- No caso de aplicações como o *Adobe Acrobat Reader* ou *Microsoft Office*:  
  **Código de segurança: \<código\>.  
  Assinatura de documento "\<título do documento/email\>".**  
  Exemplo:

![Ilustração: Formato de Mensagem de CMD 1](Pictures/Mensagem_cmd_tipo_1.png "Assinatura em Microsoft Office"){:.center}

- No caso de outras aplicações que suportem assinatura digital ou quando não é possível verificar o nome do documento:  
**Código de segurança: \<código\>.  
  Assinatura de documento "Aplicação: \<Nome da aplicação\> - Id: \<últimos 8 dígitos da hash do conteúdo a ser assinado\>".**  
  Exemplo:

![Ilustração: Formato de Mensagem de CMD 2](Pictures/Mensagem_cmd_tipo_2.png "Assinatura em Microsoft Office"){:.center}


## Integração com aplicações via interface PKCS\#11

No caso das aplicações com suporte PKCS\#11, geralmente é necessário
configurar a localização do ficheiro da aplicação, que permite o
suporte. A localização deste ficheiro, depende do sistema operativo a
ser utilizado.

**Windows:** `C:\Windows\System32\pteidpkcs11.dll`

**Linux:** `/usr/local/lib/libpteidpkcs11.so`

**MacOS:** `/usr/local/lib/libpteidpkcs11.dylib`

## Assinatura digital em aplicações comuns

Nos pontos seguintes será explicada a utilização das funcionalidades de
assinatura digital nas seguintes aplicações:

**Assinatura digital:**

- [Suite Microsoft Office](#assinatura-digital-na-suite-microsoft-office)
- [Suite LibreOffice / OpenOffice](#assinatura-digital-na-suite-libreoffice--openoffice)
- [Microsoft Outlook](#assinatura-digital-de-email-com-microsoft-outlook)
- [Adobe Acrobat Reader](#assinatura-digital-em-adobe-acrobat-reader)
- [Adobe Acrobat Reader em MacOS com PKCS#11](assinatura-digital-em-adobe-acrobat-reader-dc-em-macos-com-pkcs11)
- [Mozilla Thunderbird com PKCS#11](#assinatura-digital-de-email-com-mozilla-thunderbird)

### Assinatura digital na suite *Microsoft Office*

Nesta secção é apresentada a assinatura digital de documentos em
ficheiros *Office*, nomeadamente, nas aplicações: *Word*, *Excel* e
*PowerPoint*.

Para assinar digitalmente um documento, deverá efetuar os seguintes
passos:

1.  Aceder ao menu **Ficheiro**.
2.  Na secção **Informações** clicar no botão **Proteger Documento** e
    selecionar a opção **Adicionar uma assinatura Digital**, conforme a
    imagem abaixo:

    ![Ilustração: Assinatura em Microsoft Office](Pictures/Autenticacao.Gov_microsoft_office.png "Assinatura em Microsoft Office"){:.center}

3.  Aparecerá uma mensagem específica da aplicação que está a utilizar (*Word*, *Excel* ou *Powerpoint*), clique em **OK**.
4.  Na Caixa de diálogo **Assinar**, introduza o **Objetivo** da assinatura.
5.  Clique em assinar e introduza o seu PIN de assinatura na respetiva
    janela.
6.  O documento ficará assinado digitalmente, e ficará só de leitura de
    forma a impossibilitar alterações ao mesmo.

Poderá encontrar informação mais detalhada no seguinte link: [Adicionar/Remover uma Assinatura Digital nos ficheiros do Office](https://support.office.com/pt-pt/article/Adicionar-ou-remover-uma-assinatura-digital-nos-ficheiros-do-Office-70d26dc9-be10-46f1-8efa-719c8b3f1a2d).


### Assinatura digital na suite *LibreOffice / OpenOffice*

Nesta secção é apresentada a assinatura digital de documentos em
ficheiros *LibreOffice*, nomeadamente, nas aplicações, *Calc*, *Write* e
*Impress*. A versão utilizada neste manual foi a versão *LibreOffice
5.3*. A interface desta funcionalidade é bastante semelhante em todas as
versões a partir de 4.0.0.

Em sistemas operativos Linux, a deteção dos certificados digitais nesta
Suite depende das configurações de segurança do *Mozilla Thunderbird* ou
*Mozilla Firefox*. Assim, para que esta funcionalidade esteja disponível
deverá configurar previamente a integração com o Cartão de Cidadão no
*Mozilla Thunderbird* ou *Firefox*. Ver as instruções em: [Assinatura digital de email com Mozilla Thunderbird](#assinatura-digital-de-email-com-mozilla-thunderbird).

Para assinar digitalmente um documento, deverá efetuar os seguintes
passos:


1.  Aceder ao menu **Ficheiro** → **Assinaturas Digitais**.

2.  Aparecerá a janela com as assinaturas digitais do documento. Caso não exista ainda nenhuma assinatura, a lista aparecerá vazia conforme a imagem abaixo. Clique no botão **Assinar documento...** .

    ![Ilustração: Assinatura em LibreOffice](Pictures/Autenticacao.Gov_libre_office.png "Assinatura em LibreOffice"){:.center}
3.  Será apresentada uma janela para seleção do certificado. Deverá
    selecionar o certificado que tem o seu nome e emitido por “**EC de
    Assinatura Digital Qualificada do Cartão...**” conforme ilustrado na
    imagem abaixo:

    ![Ilustração: Assinatura em LibreOffice](Pictures/Autenticacao.Gov_libre_office2.png "Assinatura em LibreOffice"){:.center}
4.  Clique em **Aceitar** e introduza o seu PIN de assinatura na respetiva
    janela.

5.  O documento ficará assinado digitalmente.



### Assinatura digital de email com *Microsoft Outlook*

A assinatura digital no *Outlook*, por omissão, obriga a que o
certificado digital inclua o endereço de email, e este corresponda com o
email que se pretende assinar.

Nos certificados existentes no Cartão de Cidadão e na Chave Móvel Digital não existe qualquer
endereço de email. Desta forma, para que seja possível efetuar
assinaturas digitais no *Outlook* com o Cartão de Cidadão e Chave Móvel Digital, é assim necessário desativar esta
validação.

Na aplicação poderá desactivar essa validação selecionando a opção **Permitir assinatura de e-mails no Outlook** no submenu **Configuração de assinaturas** da aplicação Autenticação.gov para computador.

  ![Ilustração: Localização da opção para desativar a correspondência de e-mails nos certificado no Microsoft Outlook](Pictures/Autenticacao.Gov_configuracao_assinaturas.png "Localização da opção para desativar a correspondência de e-mails nos certificado no Microsoft Outlook"){:.center}

Alternativamente, para **desativar a correspondência com endereço de email do certificado digital**, poderá seguir as instruções disponibilizadas no *website* da *Microsoft*:

- Versão PT: <http://support.microsoft.com/kb/276597/pt>

- Versão EN (original): <http://support.microsoft.com/kb/276597/>

Para poder assinar digitalmente um email no *Outlook*, é necessário
inicialmente efetuar a respetiva configuração. Os passos descritos de
seguida, estão divididos em **configuração**, consistindo na
configuração inicial necessária, e **assinatura**, consistindo na
assinatura propriamente.

**Nota:** As imagens apresentadas são referentes ao *Microsoft Outlook 2016*.

**Configuração** – Esta operação é realizada apenas uma vez.

1.  Assegurar que a correspondência com endereço de email do certificado
    digital está desativada, conforme instruções acima.
2.  No Outlook, aceder ao menu **Ficheiro** → **Opções**

    ![Ilustração: Assinatura em Outlook](Pictures/Autenticacao.Gov_outlook.png "Assinatura em Outlook"){:.center}

3.  Clicar em **Centro de Confiança**.

    ![Ilustração: Assinatura em Outlook](Pictures/Autenticacao.Gov_outlook2.png "Assinatura em Outlook"){:.center}

4.  Selecionar a secção **Definições do Centro de Fidedignidade**.

    ![Ilustração: Assinatura em Outlook](Pictures/Autenticacao.Gov_outlook3.png "Assinatura em Outlook"){:.center}

5.  Nesta secção, selecionar a opção **Adicionar a assinatura digital às mensagens a enviar** e clicar no botão **Definições**

    ![Ilustração: Assinatura em Outlook](Pictures/Autenticacao.Gov_outlook4.png "Assinatura em Outlook"){:.center}

6.  Adicione uma descrição a esta configuração, p. ex.: “Assinatura com Cartão de Cidadão” e clique no botão **Escolher** para selecionar o certificado. Selecione o seu certificado de assinatura do Cartão de Cidadão ou  [Chave Móvel Digital](#assinatura-digital-com-chave-m%c3%b3vel-digital).

    ![Ilustração: Assinatura em Outlook: Escolha do certificado](Pictures/Autenticacao.Gov_outlook5.png "Escolha do certificado"){:.center}

7.  Na configuração **Algoritmo hash** escolha **SHA256** e clique em **OK**.

8.  Clique em **OK** em todas as janelas de configuração abertas. A
    configuração está terminada.

**Assinatura** - a efetuar cada vez que pretenda enviar um email
assinado.

1.  Ao clicar em **Enviar**, será solicitado o PIN de assinatura e o seu
    email será assinado e enviado.

    ![Ilustração: Assinatura em Outlook: Escolha do certificado](Pictures/Autenticacao.Gov_outlook6.png
      "Assinatura em Outlook: Escolha do certificado"){:.center}

### Assinatura digital em *Adobe Acrobat Reader*

**Nota**: para assinar um documento no *Adobe Acrobat Reader DC* em MacOS, siga as instruções da secção [Assinatura digital em *Adobe Acrobat Reader* em MacOS com PKCS#11](#assinatura-digital-em-adobe-acrobat-reader-em-macos-com-pkcs11).

**Nota**: a assinatura com atributos profissionais só é possível na aplicação Autenticação.Gov.

Para assinar um documento aberto no *Adobe Acrobat Reader* deve:

1. Certos documentos PDF foram criados pelo seu autor com campos de assinatura. Se for esse o caso do seu documento deve localizar o campo de assinatura que pretende utilizar e clicar no mesmo. Em seguida pode saltar para o ponto 4 deste procedimento. 
Caso contrário será necessário criar um novo campo de assinatura num local á sua escolha. Para isso deve aceder ao separador **Ferramentas** e selecionar **Certificados**.

2. Clique em **Assinar Digitalmente** na barra horizontal por cima do documento.

3. Clique e arraste o rato para determinar a posição do selo de assinatura no documento.

4. Certifique-se de que o cartão de cidadão está inserido no leitor e escolha o seu certificado de assinatura, emitido por **EC de Assinatura Digital Qualificada do Cartão de Cidadão X** (onde "X" é um número com 4 dígitos, por exemplo 0010), e carregue **Continuar**.

5. Clique em **Assinar** e escolha o nome e pasta onde deseja guardar o documento assinado.

6. Para concluir, introduza o seu PIN de assinatura. A janela de introdução de PIN deverá ter o título de "Segurança do Windows" e a mensagem "Introduza o PIN de não rejeição".

### Assinatura digital em *Adobe Acrobat Reader DC* em MacOS com PKCS#11

**Nota**: Em computadores MacOS com processador ARM, Apple M1 ou sucessores, só é possível utilizar o módulo de assinatura no Adobe Reader se esta aplicação for executada em modo Intel emulado.

Pode consultar as instruções do fabricante para desligar o modo nativo na página de ajuda: 
* https://helpx.adobe.com/pt/acrobat/kb/apple-silicon-m1-processor-support.html


Para assinar um documento aberto no *Adobe Acrobat Reader DC*, em MacOS, deve primeiro carregar o módulo PKCS#11. Para tal, siga as seguintes instruções:

1. No *Adobe Acrobat Reader DC*, aceda a **Preferências**.

    ![Ilustração: Janela para assinar em *Microsoft Word*.](Pictures/Autenticacao.Gov_macos_adobe_pref.png)

2. Selecione a Categoria **Assinaturas** e na secção "Certificados confiáveis e de identidade" pressione **Mais...**.

    ![Ilustração: Janela para assinar em *Microsoft Word*.](Pictures/Autenticacao.Gov_macos_adobe_pref2.png)

3. No separador "IDs digitais" selecione **Módulos e tokens PKCS#11** e clique **Adicionar módulo**.

    ![Ilustração: Janela para assinar em *Microsoft Word*.](Pictures/Autenticacao.Gov_macos_adobe_pkcs11.png)

4. No campo de texto insira o caminho da biblioteca: `/usr/local/lib/libpteidpkcs11.dylib` e pressione **OK**.

    ![Ilustração: Janela para assinar em *Microsoft Word*.](Pictures/Autenticacao.Gov_macos_adobe_libpath.png)

Se o seu cartão de cidadão estiver inserido no leitor e o módulo tiver sido adicionado com sucesso, ao pressionar **Atualizar** os certificados ficarão disponíveis e visíveis conforme na imagem seguinte. No caso dos certificados não ficarem disponíveis como na imagem, verifique que o cartão está inserido no leitor, reinicie o *Adobe Acrobat Reader DC* e volte a verificar.

![Ilustração: Janela para assinar em *Microsoft Word*.](Pictures/Autenticacao.Gov_macos_adobe_pkcs11_2.png)

Depois de ter adicionado o módulo PKCS#11, para assinar um documento aberto no *Adobe Acrobat Reader DC* deve, com o cartão de cidadão inserido no leitor:

1. Aceder ao separador **Ferramentas** e selecionar **Certificados**.

2. Clique em **Assinar Digitalmente** na barra horizontal por cima do documento.

3. Clique e arraste o rato para determinar a posição do selo de assinatura no documento.

4. Escolha o seu certificado de assinatura, emitido por **EC de Assinatura Digital Qualificada do Cartão de Cidadão X** (onde "X" é um número com 4 dígitos, por exemplo 0010), e carregue **Continuar**.

5. Clique em **Assinar** e escolha o nome e local onde deseja guardar o documento assinado.

6. Para concluir, introduza o seu PIN de assinatura.

### Assinatura digital com Chave Móvel Digital

Pode assinar documentos ou emails com a Chave Móvel Digital no Windows em aplicações como *Microsoft Word*, *Microsoft Excel*, *Microsoft Outlook* e *Adobe Acrobat Reader*. O procedimento é muito semelhante aos descritos nas secções anteriores mas difere em dois aspetos:

**Registo do certificado** –  deverá registar no Windows o certificado associado à sua Chave Móvel Digital.

1. No separador [Configuração de assinaturas](#configura%c3%a7%c3%a3o-de-assinaturas) da aplicação “Autenticacão.Gov” há um segmento destinado a configurações da Chave Móvel Digital com um botão **Registar**. Pressione o botão para abrir uma janela de registo do certificado.

2. Insira o número de telemóvel associado à sua conta da Chave Móvel Digital e o PIN de Assinatura da Chave Móvel Digital e pressione **OK**.

3. Se as credenciais inseridas no passo anterior estavam corretas, deverá receber um SMS no número de telemóvel associado com um código que deverá introduzir para concluir o processo de registo do certificado.

No caso de ainda não ter registado o seu certificado após uma re-ativação da Assinatura da Chave Móvel Digital ou o certificado atualmente registado estar expirado terá de repetir o procedimento de registo.

**Assinatura** – similar à assinatura com o Cartão de Cidadão com as seguintes diferenças:

- Durante a configuração ou no momento da assinatura, deverá escolher o certificado emitido por **EC de Chave Móvel Digital de Assinatura Digital Qualificada do Cartão de Cidadão X** (onde "X" é um número com 5 dígitos, por exemplo 00001 ou 00002).

![Ilustração: Janela para assinar em *Microsoft Word*.](Pictures/Assinar_Word_CMD.png)

- Após confirmar na aplicação em uso que pretende assinar será mostrada uma janela onde, tal como no registo do certificado, deverá introduzir o seu PIN de assinatura da Chave Móvel Digital. Se o PIN estiver correto, deverá receber um SMS com o código de confirmação que deverá introduzir na janela para concluir a assinatura.


### Assinatura digital de email com Mozilla Thunderbird

Para poder assinar digitalmente um email no *Thunderbird*, é necessário
inicialmente efetuar a respetiva configuração. Os passos descritos de
seguida, estão divididos em **configuração**, consistindo na
configuração inicial necessária, e **assinatura**, consistindo na
assinatura propriamente dita. Os printscreens coincidem com a versão 91 do Mozilla Thunderbird.

**Configuração** – Esta configuração é necessária efetuar uma única vez
e os passos descritos aplicam-se também à configuração dos certificados
em *Firefox*.

1. Abra as **Preferências** e selecione o separador **Privacidade e segurança**. Navegue até à secção **Segurança** -> **Certificados**.
2. Pressione o botão **Dispositivos de segurança**.

   ![Ilustração: Assinatura de E-mail com Mozilla Thunderbird](Pictures/Autenticacao.Gov_thunderbird8.png "Assinatura de E-mail com Mozilla Thunderbird"){:.center}

3. Clique no botão **Carregar**.
4. Na nova janela, preencha o nome do módulo (p.e. "Cartão de Cidadão")
e o caminho para o módulo **PKCS\#11**, que se encontra numa das seguintes
localizações (recomendação: copie o caminho para o módulo, de acordo com o
seu sistema operativo, da lista seguinte):

**Em Windows:**
- C:\\Windows\\SysWOW64\\pteidpkcs11.dll (Windows 32-bits)
- C:\\Windows\\System32\\pteidpkcs11.dll (Windows 64-bits)

**Em Linux:** /usr/local/lib/libpteidpkcs11.so

**Em MacOS:** /usr/local/lib/libpteidpkcs11.dylib

  ![Ilustração: Assinatura de E-mail com Mozilla Thunderbird](Pictures/Autenticacao.Gov_thunderbird7.png "Assinatura de E-mail com Mozilla Thunderbird"){:.center}


5. Pressione **OK** em todas as janelas.
6. Abra as **Definições de conta** e selecione a sua conta de email.
7. Selecione o separador **Criptografia de ponta a ponta**.
8. Na secção **S/MIME**, pressione o botão **Selecionar** no campo com a indicação *"Certificado pessoal para a assinatura digital"*.

    ![Ilustração: Assinatura de E-mail com Mozilla Thunderbird](Pictures/Autenticacao.Gov_thunderbird9.png "Assinatura de E-mail com Mozilla Thunderbird"){:.center}

9. Selecione o certificado com a descrição "**CARTAO DE CIDADAO:CITIZEN SIGNATURE CERTIFICATE**" e clique **OK**.

    ![Ilustração: Assinatura de E-mail com Mozilla Thunderbird](Pictures/Autenticacao.Gov_thunderbird5.png "Assinatura de E-mail com Mozilla Thunderbird"){:.center}
10. Se utilizar o Thunderbird 102 ou superior, no separador **Criptografia de ponta a ponta** selecione a caixa "Adicionar a minha assinatura por predefinição".
11. Para o Thunderbird poder utilizar o certificado do Cartão de Cidadão para assinatura é necessário registar e confiar no certificado raiz da cadeia de certificação do Estado Português.
    
  * Para cartões emitidos a partir de abril de 2020 o certificado a registar deve ser o certificado com nome "ECRaizEstado 002" disponível em: http://trust.ecee.gov.pt/ecraiz002.crt
  * Para cartões emitidos antes de abril de 2020 o certificado a registar deve ser o certificado com nome "ECRaizEstado" disponível em: http://trust.ecee.gov.pt/ecraiz.crt
  
  Para importar o certificado raíz clique em "Gerir certificados..." na janela utilizada no ponto 1 e selecione o separador "Autoridades". Deve importar o certificado raiz correto para o seu cartão e na questão "Pretende confiar em "ECRaizestado/EcRaizEstado 002" para os seguintes propósitos?" selecionar a opção: "Confiar nesta CA para identificar utilizadores de email".

**Assinatura** - a efetuar cada vez que pretenda enviar um email assinado.
Apenas necessário para versões do Thunderbird anteriores à versão 102.

1.  Abra a janela de composição de email.
2.  Clique na caixa de opções **Segurança** e clique em **Assinar digitalmente esta mensagem**. Esta opção ficará ativa.

    ![Ilustração: Assinatura de E-mail com Mozilla Thunderbird](Pictures/Autenticacao.Gov_thunderbird6.png "Assinatura de E-mail com Mozilla Thunderbird"){:.center}

3.  Ao clicar em **Enviar**, será solicitado o PIN de assinatura e o seu email será assinado e enviado.

### Assinatura digital de ficheiros DWF

**NOTA: A assinatura de ficheiros DWF apenas suporta assinatura com Cartão de Cidadão** 

**1.** Em primeiro lugar, deverá ter instalado no seu computador o **Autodesk Design Review 2013** (as versões mais recentes não disponibilizam a funcionalidade de assinatura digital).

**2.** Abra o ficheiro DWF na aplicação.

**3.** Aceda ao menu no campo superior esquerdo:

![Ilustração: Assinatura DWF 1](Pictures/Autenticacao.Gov_dwf1.png "Assinatura de ficheiros DWF"){:.center}

**4.** Aceda à opção **Security**:

![Ilustração: Assinatura DWF 2](Pictures/Autenticacao.Gov_dwf2.png "Assinatura de ficheiros DWF"){:.center}

**5.** Selecione a opção **Add Digital Signature**:

![Ilustração: Assinatura DWF 3](Pictures/Autenticacao.Gov_dwf3.png "Assinatura de ficheiros DWF"){:.center}

**6.** Nesta janela vai aparecer os certificados presentes do computador, pode carregar em **Mais Opções** para selecionar outro certificado. Certifique-se que o certificado selecionado corresponde ao de assinatura digital. Por fim, basta carregar **OK**:

![Ilustração: Assinatura DWF 4](Pictures/Autenticacao.Gov_dwf4.png "Assinatura de ficheiros DWF"){:.center}

**7.** Introduza o PIN de assinatura pedido na janela:

![Ilustração: Assinatura DWF 5](Pictures/Autenticacao.Gov_dwf5.png "Assinatura de ficheiros DWF"){:.center}

**8.** Após este procedimento, o processo de assinatura digital está concluído e vai aparecer uma janela a confirmar que assinatura está valida:

![Ilustração: Assinatura DWF 6](Pictures/Autenticacao.Gov_dwf6.png "Assinatura de ficheiros DWF"){:.center}


### Assinatura digital de ficheiros DWG

**1.** Em primeiro lugar, deverá ter instalado no seu computador uma versão atualizada da aplicação **AutoCAD** (a versão testada e cujo funcionamento foi confirmado foi a versão **AutoCAD 2022**).

**2.** Abra o ficheiro DWG na aplicação.

**3.** Aceda ao menu no campo superior esquerdo:

![Ilustração: Assinatura DWG 1](Pictures/Autenticacao.Gov_dwg1.png "Assinatura de ficheiros DWG"){:.center}

**4.** Aceda à opção **Salvar como** e selecione **Desenho**:

![Ilustração: Assinatura DWG 2](Pictures/Autenticacao.Gov_dwg2.png "Assinatura de ficheiros DWG"){:.center}

**5.** Aceda à opção **Ferramentas** e selecione **Assinaturas digitais...**:

![Ilustração: Assinatura DWG 3](Pictures/Autenticacao.Gov_dwg3.png "Assinatura de ficheiros DWG"){:.center}

**6.** Ative a checkbox **Anexar assinatura digital após salvar o desenho**. Nesta janela vai aparecer os certificados presentes do computador, selecione **EC de Chave Móvel Digital...**, se pretender assinar com a Chave Móvel, ou **EC de Assinatura Digital Qualificada do Cartão...**, se pretender assinar com o Cartão de Cidadão. Por fim, basta carregar **OK**:

**NOTA**: Para efetuar a assinatura com Chave Móvel Digital deve efetuar o procedimento descrito nesta secção [Assinatura digital com Chave Móvel Digital](#assinatura-digital-com-chave-móvel-digital) .


* Chave Móvel Digital:

![Ilustração: Assinatura DWG 4](Pictures/Autenticacao.Gov_dwg4.png "Assinatura de ficheiros DWG"){:.center}

* Cartão de Cidadão:

![Ilustração: Assinatura DWG 5](Pictures/Autenticacao.Gov_dwg5.png "Assinatura de ficheiros DWG"){:.center}

**7.** No caso da assinatura com o Cartão de Cidadão basta introduzir o PIN de assinatura (denominado na janela como PIN de não rejeição) e carregar **OK**. No caso da assinatura com Chave Móvel Digital vai-lhe ser pedido o seu pin de assinatura da Chave Móvel Digital e, após o ter introduzido corretamente, ser-lhe-á pedido o código de confirmação, enviado para o número de telemóvel registado. 

* Chave Móvel Digital:

![Ilustração: Assinatura DWG 6](Pictures/Autenticacao.Gov_dwg6.png "Assinatura de ficheiros DWG"){:.center}

![Ilustração: Assinatura DWG 7](Pictures/Autenticacao.Gov_dwg7.png "Assinatura de ficheiros DWG"){:.center}

* Cartão de Cidadão:

![Ilustração: Assinatura DWG 8](Pictures/Autenticacao.Gov_dwg8.png "Assinatura de ficheiros DWG"){:.center}

**8.** Após este procedimento, o processo de assinatura digital está concluído. Pode confirmar a assinatura, carregando com o botão direito do rato no documento, selecionando **Propriedades** e acedendo ao menu **Assinatura Digital**:

* Chave Móvel Digital:

![Ilustração: Assinatura DWG 9](Pictures/Autenticacao.Gov_dwg9.png "Assinatura de ficheiros DWG"){:.center}

* Cartão de Cidadão:

![Ilustração: Assinatura DWG 10](Pictures/Autenticacao.Gov_dwg10.png "Assinatura de ficheiros DWG"){:.center}

## Autenticação em portais WEB

Existem duas formas de se autenticar perante um portal web utilizando o
Cartão de Cidadão:

-   Autenticação por certificado cliente através do navegador.
-   Autenticação por certificado através do portal **Autenticação.gov.pt**.

A forma de autenticação depende totalmente da configuração do website, não sendo possível ao utilizador escolher uma ou outra forma.

A **Autenticação por certificado cliente através do navegador**, tende a
ser descontinuada, visto a apresentar desvantagens na recolha de dados,
no entanto alguns sítios ainda utilizam esta forma.

Para poder utilizar este método de autenticação, tem de ter
obrigatoriamente a aplicação instalada no seu computador.

Neste caso utilizando Sistema Operativo *Windows* os browsers *Internet
Explorer*, *Microsoft Edge* e *Google Chrome* não exigem nenhuma
configuração uma vez registado o certificado de autenticação do Cidadão.

Para o *Mozilla Firefox* em qualquer Sistema Operativo é necessário
efetuar algumas configurações descritas em [Configurar autenticação para *Mozilla Firefox*](#configurar-autentica%c3%a7%c3%a3o-para-mozilla-firefox).

A autenticação por certificado através do portal autenticação.gov.pt não exige a instalação da aplicação mas sim do plugin **Autenticação.Gov**.

Para mais informação, consulte esta página de ajuda:

* <https://autenticacao.gov.pt/fa/ajuda/autenticacaogovpt.aspx>

De forma a que a configuração do seu computador suporte qualquer uma das
alternativas, recomenda-se que instale a aplicação no seu computador
e também o plugin **Autenticação.Gov**.


### Configurar autenticação para *Mozilla Firefox*

Para configurar o *Mozilla Firefox* tem que carregar o módulo PKCS\#11 do Cartão do Cidadão.

Na versão 73.0.1 (para outras versões deverá ser semelhante):

1. Nas opções do *Mozilla Firefox* aceda a **Privacidade e Segurança**. Pode aceder diretamente inserindo `about:preferences#privacy` na barra de endereço.

2. Navegue até ao final da página e, na secção **Certificados**, carregue em **Dispositivos de segurança...** para abrir a janela "Gestor de dispositivos".

![Ilustração: Carregar PKCS\#11 no Mozilla Firefox](Pictures/Firefox_carregar_pkcs11.png
  "Carregar PKCS\#11 no Mozilla Firefox"){:.center}

3. Pressione **Carregar**. Preencha o nome do módulo, por exemplo "PKCS\#11 do Cartao de Cidadao" (evite o "ç" e "ã"). Seguidamente, carregue em **Procurar...** e navegue até ao módulo PKCS#11 do Cartão de Cidadão, cuja localização se encontra descrita na secção [Integração com aplicações](#integra%c3%a7%c3%a3o-com-aplica%c3%a7%c3%b5es).

**Nota**: em MacOS o que se recomenda neste passo é copiar e colar no campo "Nome do ficheiro do módulo" o seguinte nome: `/usr/local/lib/libpteidpkcs11.dylib`

4. Pressione **Ok** nas janelas abertas para terminar.

# Resolução de Problemas

## Exibida mensagem de erro quando se tenta adicionar o módulo PKCS\#11 no *Firefox / Thunderbird*

Para que consiga adicionar o módulo PKCS\#11 no Firefox tem de ter um
leitor de cartões instalado no seu computador.

Certifique-se que o leitor está ligado e instalado e um cartão inserido
antes de adicionar o módulo PKCS\#11.

Caso esteja a utilizar uma versão de MacOS ou Linux 64-bit terá de
utilizar uma versão 64-bit do *Firefox* / *Thunderbird*.

## Não é possível adicionar o módulo PKCS\#11 ao *Adobe Acrobat Reader* em *MacOS*


Em versões anteriores do *Adobe Acrobat Reader* para *MacOS* não é possível
adicionar o módulo PKCS\#11. Recomendamos a atualização do *Adobe
Acrobat Reader* para a versão **DC**.

## Impossibilidade de assinatura em *Adobe Reader*, *Microsoft Office* e *LibreOffice* com Cartão de Cidadão

Deverá aceder ao ficheiro `pteidmdrv.inf`, presente por defeito na
diretoria `C:\Program Files\Portugal Identity Card\PTeID Minidriver` (ou na directoria
selecionada durante a instalação). Após ter aberto a directoria, abra
o menu de opções do ficheiro e selecionar a opção **Instalar**.

![Ilustração: Impossibilidade de assinatura](Pictures/Autenticacao.Gov_web_impossibilidade.png
  "Impossibilidade de assinatura"){:.center}

Após a escolha desta opção, poderá aparecer uma janela de diálogo (Ver
imagem seguinte) com o título "Ficheiros Necessários", na qual terá de
selecionar a pasta "drivers" que esta na diretoria `C:\Windows\System32`.

![Ilustração: Impossibilidade de assinatura](Pictures/Autenticacao.Gov_web_impossibilidade2.png
  "Impossibilidade de assinatura"){:.center}

Em caso de problemas verifique se está relacionado com o tópico [Problemas com a nova cadeia de confiança](#problemas-com-a-nova-cadeia-de-confiança).

## O leitor de cartões está instalado mas não é detetado pela aplicação do Cartão de Cidadão

### Windows <!-- omit in toc -->

1.  Verifique se o leitor de cartões é compatível com o standard PC/SC (consulte a documentação do leitor de cartões ou contacte o fabricante).

2.  Verifique se os controladores do leitor estão corretamente instalados (consulte a documentação do leitor de cartões).

3.  Verifique se o serviço “Cartão Inteligente” (Smart Card) está iniciado:


    a\) Aceda ao **Painel de Controlo** e em seguida aceda a **Ferramentas de Administração**
    
    b\) Clique em **Serviços**
    
    c\) Verifique se o serviço “**Cartão Inteligente**” (Smart Card) está iniciado (Started). Caso não esteja, clique com o botão direito no serviço e clique em **Start**.
    
    d\) Desligue o leitor do computador.
    
    e\) Encerre a aplicação do Cartão de Cidadão.
    
    f\) Volte a inserir o leitor e abra novamente a aplicação.

4.  Se estiver a usar uma ligação a uma máquina remota e prentende partilhar o leitor de cartões, verifique se:

    a\) Está a ligar o leitor de cartões no computador local

    b\) Escolheu o **Smart Card** na lista de dispositivos e recursos locais que pretende utilizar na ligação remota (Ver imagem seguinte).

![Ilustração: Ligação a máquina remota e partilha de leitor de cartões](Pictures/Autenticacao.Gov_Ligacao_Remota_SmartCard.png "Ligação a máquina remota e partilha de leitor de cartões"){:.center}

### Linux <!-- omit in toc -->

1.  Verifique se o leitor de cartões é compatível com o standard PC/SC (consulte a documentação do leitor ou contacte o fabricante).
2.  Verifique se os controladores do leitor estão corretamente instalados (consulte a documentação do leitor).
3.  Verifique se o **pcsc daemon** está instalado e em execução:

    a\) Numa janela de terminal execute o seguinte comando:

    `ps aux`

    b\) Procure uma referência ao processo pcscd.

    c\) Caso não esteja listado por favor inicie o serviço através do
comando:

    `sudo /etc/init.d/pcscd start`

    d\) Caso obtenha uma mensagem de erro é possível que o daemon não esteja
instalado. Utilize o seu gestor de pacotes para instalar o pcscd (por
vezes já vem incluído no pacote pcsc-lite).

## Não são detetados quaisquer certificados durante a tentativa de assinatura na suite *LibreOffice / Apache OpenOffice*

A suite *LibreOffice / OpenOffice* em Linux, utiliza as configurações da aplicação *Mozilla Firefox* (ou como alternativa, *Thunderbird*) para a deteção dos certificados.

Para que os certificados passem a ser detetados na *Suite LibreOffice / Apache OpenOffice*, terá que efetuar a respetiva configuração no *Mozilla Firefox*, caso tenha esta aplicação instalada. Caso não tenha, poderá configurar o *Mozilla Thunderbird* para poder assinar documentos no *LibreOffice*.

Para mais informações consultar a página de ajuda:

-   <https://help.libreoffice.org/Common/Applying_Digital_Signatures/pt>

## Problemas gráficos na aplicação

O modo de renderização que está configurado na aplicação no momento da instalação é o que permite o melhor desempenho na maioria dos casos: a renderização por Hardware. No entanto este modo exige um driver OpenGL funcional no sistema para a placa gráfica do computador.

No caso de existirem problemas gráficos, recomenda-se alterar o modo de renderização gráfica. Pode fazê-lo na secção Aceleração gráfica do submenu [Configuração da aplicação](#configuração-da-aplicação).

**No caso da aplicação não arrancar ou tiver problemas no arranque**, é possível alterar essa opção de três formas distintas:

1. [Exclusivo para Windows a partir da versão 3.4.0 da aplicação] Na pasta de instalação da aplicação (que por omissão é `C:\Program Files\Portugal Identity Card`) existem três atalhos que permitem abrir a aplicação alterando o modo de renderização gráfica. Os atalhos são ícones clicáveis com o símbolo da aplicação e têm os seguintes nomes:

    - **Autenticação.Gov Software** para renderização por Software (OpenGL);
    - **Autenticação.Gov Hardware** para renderização por Hardware (Placa gráfica);
    - **Autenticação.Gov Direct3d** para renderização por Software (ANGLE, que emula o OpenGL usando Direct3D);

    No caso de existirem problemas gráficos, ou a aplicação não arrancar, deverá experimentar as três opções e ver qual tem melhor resultado. A última opção escolhida, ficará guardada e poderá voltar a arrancar a aplicação clicando no ícone no ambiente de trabalho ou no menu iniciar.

2. Usando as configurações do software Autenticação.gov (via Chaves de Registo em Windows ou ficheiro de configuração em Linux e MacOS).

   - Em **Windows**, a chave de registo:  
**HKEY\_CURRENT\_USER\\Software\\PTEID\\configuretool\\graphics\_accelaration**;
   - Em **Linux**, a chave com nome "graphics_accelaration" na secção “configuretool” do ficheiro:  
**$HOME/.config/pteid.conf**;
   - Em **MacOS**, a chave com nome "graphics_accelaration" na secção “configuretool” do ficheiro:  
**$HOME/Library/Preferences/pteid.conf**.

   Os valores que a chave pode tomar são:
   - 0 para renderização por Software (OpenGL);
   - 1 para renderização por Hardware (Placa gráfica);
   - 2 (Exclusivo para Windows) para renderização por Software (ANGLE, que emula o OpenGL usando Direct3D).

3.  Usando o [Interface de linha de comandos](#interface-de-linha-de-comandos).


Em ambiente empresariais deve alterar esta configuração conforme descrito no capítulo [Instruções de configuração em ambientes empresariais](#instruções-de-configuração-em-ambientes-empresariais).


## Problemas com placas gráficas integradas

No caso de existirem problemas com placas gráficas, consulte o tópico [Problemas gráficos na aplicação](#problemas-gráficos-na-aplicação).

## Não é possível mover/arrastar a aplicação (Linux em Wayland)

Em sistemas Linux que utilizem um sistema de interface gráfico baseado em Wayland foram detetados vários problemas de interação, entre os quais a impossibilidade de mover a janela arrastando-a a partir da barra de título.

Recomendamos a seguinte solução temporária, assumindo que existe o componente XWayland:  
Usar o parâmetro `-platform xcb`, ou a variável de ambiente `QT_QPA_PLATFORM=xcb`, para forçar a aplicação a correr em modo X11, usando o XWayland.

```
$ eidguiV2 -platform xcb
```
ou
```
$ QT_QPA_PLATFORM=xcb eidguiV2
```

## Aplicação não arranca

No caso da aplicação não arrancar e consequentemente não ser possível alterar as configurações da aplicação na aplicação, é possível alterar as opções da aplicação usando as configurações do software Autenticação.gov (via Chaves de Registo em Windows ou ficheiro de configuração em Linux e MacOS).

Uma causa que pode causar problemas está relacionado com a placa gráfica do computador, consulte o tópico [Problemas gráficos na aplicação](#problemas-gráficos-na-aplicação).

## Problemas com a nova cadeia de confiança

O Sistema de Certificação Eletrónica do Estado colocou em produção um novo certificado raiz do Estado. Em consequência, os certificados do Cartão de Cidadão passaram a ser emitidos, desde 4 de abril de 2020, sob a nova cadeia de confiança do Estado Português. O certificado raiz da nova cadeia, no caso de não ser disponibilizado pelo sistema operativo, deverá ser instalado para que os certificados dos cartões emitidos após essa data sejam automaticamente confiáveis pelo sistema operativo Windows, assim como por algumas aplicações que não utilizam a lista de serviços confiáveis publicada pela União Europeia.

Pode instalar o certificado na *Store* do Windows através da aplicação do Cartão de Cidadão no submenu [Configuração de assinaturas](#configuração-de-assinaturas). Para tal, clique no botão **Instalar certificado** na secção **Novo certificado raiz do Estado**. O certificado será adicionado à *Store* “Trusted Root Certification Authorities” associada ao “Current User”. **Nota:** A funcionalidade de instalar certificado automaticamente só está disponível a partir da versão 3.4.0 da aplicação, versões anteriores deve usar o método manual apresentado em seguida.

Alternativamente, pode instalar o certificado manualmente seguindo as instruções no manual de instalação da cadeia do SCEE disponível em [https://www.ecce.gov.pt/certificados/](https://www.ecce.gov.pt/certificados/). Deverá seguir as instruções específicas para o “Certificado da Entidade de Certificação Eletrónica do Estado - ECRaizEstado 002” (passos 4.1 a 4.10 do manual).

## Problemas na validação das assinaturas 

Em caso de problemas na validação das assinaturas, verifique se está relacionado com o tópico [Problemas com a nova cadeia de confiança](#problemas-com-a-nova-cadeia-de-confiança).

## Problemas com ficheiros PDF não suportados

A aplicação Autenticação.Gov atualmente não suporta os seguintes ficheiros PDF:
  - Ficheiros PDF Encriptados;
  - Ficheiros PDF com formulários XFA (criados, por exemplo, na aplicação *Adobe LiveCyle*).

No entanto, é possível assinar estes ficheiros com o cartão de cidadão ou Chave Móvel Digital
recorrendo a uma aplicação externa, por exemplo o *Adobe Acrobat Reader*. Para o fazer siga os passos
descritos na secção [Assinatura digital em *Adobe Acrobat Reader*](#assinatura-digital-em-adobe-acrobat-reader).

## Serviços online usados pela aplicação

Algumas funcionalidades da aplicação requerem a ligação a serviços online para funcionarem corretamente. É por isso necessário garantir que não existe *firewall* ou outro *software* na rede local que impeça a ligação a estes serviços.

Os *hostnames* e respetivos portos utilizados são listados em seguida por funcionalidade.

**Assinatura com Chave Móvel Digital:**

- cmd.autenticacao.gov.pt (porto 443)

**Validação de certificados:**

Servidores OCSP:
- ocsp.ecee.gov.pt   (porto 80 e 443)
- ocsp.multicert.com (porto 80)
- ocsp.root.cartaodecidadao.pt (porto 80)
- ocsp.auc.cartaodecidadao.pt  (porto 80)
- ocsp.asc.cartaodecidadao.pt  (porto 80)

Servidores CRL:
- crls.ecee.gov.pt (porto 80)
- pkiroot.multicert.com (porto 80)
- pki.cartaodecidadao.pt (porto 80)

**Assinatura com atributos Profissionais:**

- scap.autenticacao.gov.pt (porto 443)
- autenticacao.gov.pt (porto 443) (apenas para carregamento com CMD)

**Alteração de morada:**

- pki.cartaodecidadao.pt (porto 443)

**Atualização da aplicação:**
- autenticacao.gov.pt (porto 443)
- aplicacoes.autenticacao.gov.pt (porto 443)

**Atualização de certificados e notícias:**
- raw.githubusercontent.com
  - Na obtenção do ficheiro em https://raw.githubusercontent.com/amagovpt/autenticacao.gov/master/pteid-mw-pt/_src/eidmw/news.json
- github.com
  - URLs da forma: https://github.com/amagovpt/autenticacao.gov/blob/master/pteid-mw-pt/_src/eidmw/misc/certs/NOME_DO_FICHEIRO

**Documentação:**
- URLs da forma: https://amagovpt.github.io/docs.autenticacao.gov/NOME_DO_FICHEIRO
  
**Selo temporal (por defeito):**

- ts.cartaodecidadao.pt (porto 80)

## Obtenção do relatório para análise através do menu Configurações

A partir da versão 3.5.0 da aplicação, para todos os sistemas operativos, para ajudar a diagnosticar um problema com a aplicação local **Autenticação.gov**, é possível criar um **Relatório de Suporte**. Este **Relatório de Suporte** contém os ficheiros de log com informações sobre o funcionamento da aplicação, essenciais para o diagnóstico de eventuais problemas.

Para obter o **Relatório de Suporte** deve:

1. Abrir a aplicação e navegar até ao menu **Configurações -> Configurações da Aplicação**.
2. Na secção "Modo diagnóstico" selecionar a opção **Ativar modo diagnóstico**.
3. Reiniciar a aplicação.
4. Após reiniciar, a aplicação deverá indicar o modo de diagnóstico ativo na barra de topo da aplicação, bem como a cor da mesma barra mudar para vermelho. Ver imagem seguinte.
5. Repetir a ação que resultou na situação de erro/problema. Desta forma, com o modo diagnóstico ativo, as informações sobre o erro/problema constantes nos ficheiros de log serão mais detalhadas.
6. Voltar a navegar até ao menu **Configurações -> Configurações da Aplicação**.
7. Pressionar o botão **Criar relatório**. Ao pressionar o botão **Criar relatório** é criado um ficheiro zip no Ambiente de Trabalho que contém os ficheiros de log da aplicação, com um nome deste tipo: **Autenticacao.gov_logs_DATA.zip**. 
8. Por fim, deve enviar este ficheiro zip, juntamente com uma descrição o mais detalhada possível do problema, para o contacto de suporte através do email **cartaodecidadao@irn.mj.pt**.

    ![Ilustração: Criar Relatório de Suporte](Pictures/Autenticacao.Gov_Relatorio_Suporte.png "Criar Relatório de Suporte"){:.center}


## Remoção de metadados de ficheiros PDF

A remoção de metadados de ficheiros PDF, através, por exemplo, da opção "Imprimir para PDF" de alguns 
browsers, pode remover as assinaturas digitais existentes no ficheiro, mesmo que o selo visual se mantenha.  
Por este motivo, caso seja necessária a remoção de metadados, através desse ou de outro processo 
semelhante, esta deve ser feita antes de realizar qualquer assinatura.

# Interface de linha de comandos

**Nota:** Esta funcionalidade só está disponível a partir da versão 3.3.0 da aplicação.

Quando executada a partir da linha de comandos, a aplicação Autenticação.gov para computador aceita alguns modos e opções descritos nas seguintes subsecções.

## Consultar ajuda e versão

A opção *--help* (ou *-h*) permite, para cada modo,  consultar as opções disponíveis na linha de comandos.

A versão instalada pode ser consultada com *--version* (ou *-v*).

Exemplo (Linux):
```
$ eidguiV2 -h
```
## Atalho para configurar modo de renderização gráfica

É possível configurar o modo de renderização gráfica com umas das seguintes opções:
- *-w*: (Exclusivo para Windows) para renderização por Software (ANGLE, que emula o OpenGL usando Direct3D).
- *-s*: para renderização por Software (OpenGL);
- *-c*: para renderização por Hardware (Placa gráfica)(Valor por omissão);

No caso de existirem problemas gráficos, ou a aplicação não arrancar, deverá experimentar as três opções e ver qual tem melhor resultado. A última opção escolhida, ficará guardada e poderá voltar a arrancar a aplicação clicando no ícone no ambiente de trabalho, no menu iniciar ou mesmo pela linha de comandos sem passar este parâmetro.

**Nota:** Disponível a partir da versão 3.4.0 da aplicação.

No caso de existirem problemas com placas gráficas, consulte o tópico [Problemas gráficos na aplicação](#problemas-gráficos-na-aplicação).

Exemplo (Linux):
```
$ eidguiV2 -c
```

Exemplo (Windows):
```
$ "C:\Program Files\Portugal Identity Card\pteidguiV2.exe" -c
```

## Atalho para submenu de assinatura

Através da interface de linha de comandos, é possível iniciar a aplicação diretamente no submenu de
assinatura digital.

O atalho para o submenu de assinatura (*sign*) requer os caminhos dos ficheiros a serem carregados
para assinatura.

As opções suportadas são as seguintes:
  - *--destino DESTINO* (*-d DESTINO*): configura a pasta de destino do ficheiro assinado. A pasta
    de destino não será pedida ao utilizador no momento da assinatura.
  - *--tsa*: ativa a assinatura com timestamp;
  - *--motivo MOTIVO* (*-m MOTIVO*): configura o campo 'motivo' da assinatura;
  - *--localidade LOCALIDADE* (*-l LOCALIDADE*): configura o campo 'localidade' da assinatura.

Exemplos (Linux):
```
$ eidguiV2 sign -d /home/user/Documents/ ficheiro.pdf
```
```
$ eidguiV2 sign --tsa -m "motivo" -l "localidade" -d /home/user/Documents/ \
ficheiro1.pdf ... ficheiroN.pdf
```

Exemplo (Windows):
```
> "C:\Program Files\Portugal Identity Card\pteidguiV2.exe" sign^
 -d C:\Users\USER\Documents ficheiro.pdf
```

**Nota:** As opções passadas por parâmetros, que contêm um caracter espaço, devem ser colocadas
entre aspas. Por exemplo (Linux):
```
$ eidguiV2 sign -d /home/user/Documents/ "ficheiro para assinar.pdf"
```

# Instruções de configuração em ambientes empresariais

## Configurações através de chaves de registo Windows

As configurações do software Autenticação.gov são guardadas em *Windows*
em chaves de registo sendo que as alterações feitas pelo utilizador no
interface gráfico se sobrepõem aos valores predefinidos e ficam
guardadas em sub-chaves de:

`HKCU\Software\PTEID`

Pode-se, no entanto, configurar de forma padronizada uma instalação
adicionando alguns valores no registo do *Windows* para todos os
utilizadores da máquina após a instalação do software, usando as chaves listadas
abaixo, sub-chaves da chave raiz: **`HKLM\Software\PTEID`**

**Nota:** Não se devem nunca remover ou alterar os seguintes registos:

`HKLM\Software\PTEID\general\install_dirname`

`HKLM\Software\PTEID\general\certs_dir`

Os seguintes registos podem ser adicionados:

---
`HKLM\Software\PTEID\logging\log_level`
- **Tipo**: String (debug, info, warning, error)
- **Descrição**: Nível de detalhe do log do Middleware e da aplicação.
- **Valor por omissão**: error

---

`HKLM\Software\PTEID\logging\log_dirname`
  - **Tipo**: String
  - **Descrição**: Directoria onde são gerados os ficheiros de log do Middleware e da aplicação.
  - **Valor por omissão**: `C:\Program Files\Portugal Identity Card\log`

---

`HKLM\Software\PTEID\logging\log_prefix`
  - **Tipo**: String
  - **Descrição**: Prefixo do nome dos ficheiros de log.
  - **Valor por omissão**: `.PTEID_`

---

`HKLM\Software\PTEID\general\cache_dirname`

  - **Tipo**: String
  - **Descrição**: Directoria onde é guardada a cache do Middleware e da aplicação.
  - **Valor por omissão**: `C:\Users\[User]\AppData\Roaming\.pteid-ng`

---

`HKLM\Software\PTEID\general\cache_enabled`

  - **Tipo**: Número (0 / 1)
  - **Descrição**: Ativar (1) ou desativar (0) a cache relativa aos dados do Cartão do Cidadão.
  - **Valor por omissão**: 1 (ativa)

---

`HKLM\Software\PTEID\general\scap_host`

  - **Tipo**: String
  - **Descrição**: Hostname do serviço de Atributos Profissionais (SCAP).
  - **Valor por omissão**: `scap.autenticacao.gov.pt`

---

`HKLM\Software\PTEID\general\scap_port`

  - **Tipo**: Número (1 a 65535)
  - **Descrição**: Porto do serviço de Atributos Profissionais (SCAP).

---

`HKLM\Software\PTEID\general\use_pinpad`

  - **Tipo**: Número (0 / 1)
  - **Descrição**:  Usar funcionalidade de PINPAD.
  - **Valor por omissão**: 1 (Sim)

---

`HKLM\Software\PTEID\general\auth_pin_cache_normal`

  - **Tipo**: Número (0 / 1)
  - **Descrição**:  Permitir cache do PIN de autenticação via minidriver.
  - **Valor por omissão**: 0 (Não). Para versões anteriores à 3.1.0 o valor por omissão é 1.
  - **Configurável a partir da versão**: 3.1.0

---

`HKLM\Software\PTEID\configuretool\start_autoupdate`

  - **Tipo**: Número (0 / 1)
  - **Descrição**:  Verificar se existem atualizações de software ou certificados no arranque da aplicação e notificar o utilizador
  - **Valor por omissão**: 1 (Sim)

---

`HKLM\Software\PTEID\configuretool\graphics_accelaration`

  - **Tipo**: Número (0 / 1 / 2 (somente em Windows))
  - **Descrição**:  Modo de renderização gráfica. 0 para renderização por Software (OpenGL), 1 para renderização por Hardware (Placa gráfica) ou 2 para rederização por Software (ANGLE que emula o OpenGL usando Direct3D).
  - **Valor por omissão**: 1 (Placa gráfica)

---

`HKLM\Software\PTEID\configuretool\start_with_windows`

  - **Tipo**: Número (0 / 1)
  - **Descrição**:  Arrancar a aplicação com o Windows.
  - **Valor por omissão**: 1 (Sim)

---

`HKLM\Software\PTEID\configuretool\registrate_certificate`

  - **Tipo**: Número (0 / 1)
  - **Descrição**:  Registar certificados no Windows com a inserção do cartão. As opções de registo e remoção de certificados requerem que esta aplicação esteja em funcionamento (minimizada ou maximizada).
  - **Valor por omissão**: 1 (Sim)

---

`HKLM\Software\PTEID\configuretool\remove_certificate`

  - **Tipo**: Número (0 / 1)
  - **Descrição**:  Remover certificados do Windows com a remoção do cartão.
  - **Valor por omissão**: 0 (Não)

---

`HKLM\Software\PTEID\proxy\use_system_proxy`

  - **Tipo**: Número (0 / 1)
  - **Descrição**:  Utilizar servidor de proxy definido no Windows/ MacOS.
  - **Valor por omissão**: 0 (Não)

---

`HKLM\Software\PTEID\proxy\proxy_host`

  - **Tipo**: String (hostname ou endereço IP)
  - **Descrição**:  Endereço do servidor de proxy.

---

`HKLM\Software\PTEID\proxy\proxy_port`

  - **Tipo**: Número (1 a 65535)
  - **Descrição**:  Porto TCP do servidor de proxy.

---

`HKLM\Software\PTEID\certificatecache\cert_cache_validity`

  - **Tipo**: Número (0 a 2147483647)
  - **Descrição**:  Tempo de cache local (em segundos) do estado de validade dos certificados.
  - **Valor por omissão**: 60

---

`HKLM\Software\PTEID\xsign\tsa_url`

  - **Tipo**: String
  - **Descrição**:  Servidor de timestamps usado na assinatura de documentos no formato `http(s)://HOST:PORTO`
  - **Valor por omissão**: `http://ts.cartaodecidadao.pt/tsa/server`

---

## Configurações através de ficheiro de configuração em Linux e MacOS

As configurações do software Autenticação.gov são guardadas em Linux e
MacOS num ficheiro de configuração. Este ficheiro de
configuração está localizado no seguinte caminho:

**Linux:** `$HOME/.config/pteid.conf`

**MacOS:** `$HOME/Library/Preferences/pteid.conf`

onde **$HOME** indica a directoria Home do utilizador de sistema.

O formato do ficheiro segue o [formato INI](https://en.wikipedia.org/wiki/INI_file) com a respectiva secção de
configuração a ser indicada por uma tag. Os valores que se podem
especificar em cada secção/tag são os que foram indicados na tabela anterior
referente às [Configurações através de chaves de registo Windows](#configura%c3%a7%c3%b5es-atrav%c3%a9s-de-chaves-de-registo-windows).

Por exemplo para ativar manualmente o modo de diagnóstico devem ser adicionadas ao ficheiro as 2 seguintes linhas:
```
[logging]
log_level=debug
```

## Instalação automatizada em ambientes Windows

Para instalar o software de forma automatizada é necessário seguir o
seguinte procedimento (com permissões de administrador):

1.  Adicionar o certificado de *codesigning* da AMA – Agência para a
    Modernização Administrativa à Store “Trusted Publishers” associada
    ao “Local Computer” através da ferramenta MMC ou através do seguinte
    comando:

    `certmgr -add AMA\_codesigning.cer -c -s -r localMachine TrustedPublisher`

    O certificado pode ser obtido a partir do instalador MSI visualizando
    a assinatura do ficheiro no menu de contexto em:

    **Properties** → **Digital Signatures** → **Details**

2.  Se o sistema operativo for *Windows 7* deverá ser instalado um
    *Hotfix* fornecido pela *Microsoft* para resolver uma
    incompatibilidade com o certificado de *codesigning* do software. Para
    tal seguir as instruções deste artigo:

    https://support.microsoft.com/en-us/help/2921916/the-untrusted-publisher-dialog-box-appears-when-you-install-a-driver-i

3.  Tendo já obtido o instalador em formato MSI podemos instalar o
    software sem interacção com o utilizador usando o seguinte comando:

    `msiexec /i Autenticacao.gov-xxx.msi /qn`

    Se se pretende evitar o reinício do sistema após a instalação deve
    ser adicionado ao comando o parâmetro */norestart* .

## Informação sobre servidores de Proxy

### Configuração em *Windows* <!-- omit in toc -->

Se a máquina em questão tiver um proxy correctamente configurado no Windows, seja por
IP/Hostname + Porto ou por script de autoconfiguração (PAC file) não é necessária qualquer
configuração no MW.

O software tem neste momento uma limitação com alguns tipos de
servidores de proxy designadamente com autenticação NTLM ou Kerberos.
Para utilizar as funcionalidades que exigem acesso à Internet
(confirmação de morada, validação de certificados, assinatura com
atributos profissionais ou assinatura com Chave Móvel) será necessário
nestes ambientes uma reconfiguração de rede ou o uso de uma proxy aberta
ou com autenticação Basic.

### Configuração em *MacOS* <!-- omit in toc -->

Em MacOS é suportada a proxy do sistema mas apenas se for configurada por IP/Hostname + Porto

<!-- End_of_content -->
_________________

Desenvolvido pelo Estado Português<br>
(Agência para a Modernização Administrativa, IP e Instituto dos Registos e do Notariado, IP)<br>
© Copyright (C) 2010-2021
