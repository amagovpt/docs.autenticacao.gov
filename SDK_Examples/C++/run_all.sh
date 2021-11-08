#!/bin/bash

#Possible options for example programs () : "-identity", "-addr", "-auth", "-sign", "-all"
#Only -identity can be used with no PIN prompts

OPTION=$1

if [ -z $OPTION ]; then
    echo "Usage: ./run_all.sh [-identity/-addr/-auth/-sign/-all]"
    exit 0
fi

if [ $OPTION = "-identity" ]; then

    echo "Running C++ SDK Example - ReadCard"

    g++ -c -std=c++17 ReadCard.cpp
    g++ -o ReadCard.example ReadCard.o -lpteidlib 
    ./ReadCard.example

    echo "Running C++ SDK Example - GetPhoto"

    g++ -c -std=c++17 GetPhoto.cpp
    g++ -o GetPhoto.example GetPhoto.o -lpteidlib
    ./GetPhoto.example -png "files/image.png"
    ./GetPhoto.example -jp2 "files/image.jp2"

    echo "Running C++ SDK Example - PinInfo"

    g++ -c -std=c++17 PinInfo.cpp
    g++ -o PinInfo.example PinInfo.o -lpteidlib 
    ./PinInfo.example

elif [ $OPTION == "-addr" ]; then

    echo "Running C++ SDK Example - ReadAddress"

    g++ -c -std=c++17 ReadAddress.cpp
    g++ -o ReadAddress.example ReadAddress.o -lpteidlib 
    ./ReadAddress.example

    echo "Running C++ SDK Example - GetXML"

    g++ -c -std=c++17 GetXML.cpp
    g++ -o GetXML.example GetXML.o -lpteidlib 
    ./GetXML.example

elif [ $OPTION == "-auth" ]; then

    echo "Running C++ SDK Example - ReadAndWriteNotes"

    g++ -c -std=c++17 ReadAndWriteNotes.cpp
    g++ -o ReadAndWriteNotes.example ReadAndWriteNotes.o -lpteidlib
    ./ReadAndWriteNotes.example

elif [ $OPTION == "-sign" ]; then

    echo "Running C++ SDK Example - SignPDFDocument"

    g++ -c -std=c++17 SignPDFDocument.cpp
    g++ -o SignPDFDocument.example SignPDFDocument.o -lpteidlib 
    ./SignPDFDocument.example "files/input.pdf" "files/output.pdf"

    echo "Running C++ SDK Example - SignPDFMultipleFiles (3 documents)"

    g++ -c -std=c++17 SignPDFMultipleFiles.cpp
    g++ -o SignPDFMultipleFiles.example SignPDFMultipleFiles.o -lpteidlib
    ./SignPDFMultipleFiles.example "files/" "files/input1.pdf" "files/input2.pdf" "files/input3.pdf"

    echo "Running C++ SDK Example - SignPDFDocumentCustomSize"

    g++ -c -std=c++17 SignPDFCustomSize.cpp
    g++ -o SignPDFCustomSize.example SignPDFCustomSize.o -lpteidlib
    ./SignPDFCustomSize.example "files/input.pdf" "files/output_custom.pdf"

elif [ $OPTION == "-signCMD" ]; then

    echo "Running C++ SDK Example - SignFileCMD"

    g++ -c -std=c++17 SignFileCMD.cpp
    g++ -o SignFileCMD.example SignFileCMD.o -lpteidlib
    ./SignFileCMD.example

    echo "Running C++ SDK Example - SignFileSigningDevice"

    g++ -c -std=c++17 SignFileSigningDevice.cpp
    g++ -o SignFileSigningDevice.example SignFileSigningDevice.o -lpteidlib
    ./SignFileSigningDevice.example -CMD

elif [ $OPTION == "-all" ]; then

    echo "Running C++ SDK Example - ReadCard"

    g++ -c -std=c++17 ReadCard.cpp
    g++ -o ReadCard.example ReadCard.o -lpteidlib 
    ./ReadCard.example

    echo "Running C++ SDK Example - GetPhoto"

    g++ -c -std=c++17 GetPhoto.cpp
    g++ -o GetPhoto.example GetPhoto.o -lpteidlib
    ./GetPhoto.example -png "files/image.png"
    ./GetPhoto.example -jp2 "files/image.jp2"

    echo "Running C++ SDK Example - PinInfo"

    g++ -c -std=c++17 PinInfo.cpp
    g++ -o PinInfo.example PinInfo.o -lpteidlib 
    ./PinInfo.example

    echo "Running C++ SDK Example - ReadAddress"

    g++ -c -std=c++17 ReadAddress.cpp
    g++ -o ReadAddress.example ReadAddress.o -lpteidlib 
    ./ReadAddress.example

    echo "Running C++ SDK Example - GetXML"

    g++ -c -std=c++17 GetXML.cpp
    g++ -o GetXML.example GetXML.o -lpteidlib 
    ./GetXML.example

    echo "Running C++ SDK Example - ReadAndWriteNotes"

    g++ -c -std=c++17 ReadAndWriteNotes.cpp
    g++ -o ReadAndWriteNotes.example ReadAndWriteNotes.o -lpteidlib
    ./ReadAndWriteNotes.example

    echo "Running C++ SDK Example - SignPDFDocument"

    g++ -c -std=c++17 SignPDFDocument.cpp
    g++ -o SignPDFDocument.example SignPDFDocument.o -lpteidlib 
    ./SignPDFDocument.example "files/input.pdf" "files/output.pdf"

    echo "Running C++ SDK Example - SignPDFMultipleFiles"

    g++ -c -std=c++17 SignPDFMultipleFiles.cpp
    g++ -o SignPDFMultipleFiles.example SignPDFMultipleFiles.o -lpteidlib
    ./SignPDFMultipleFiles.example "files/" "files/input1.pdf" "files/input2.pdf" "files/input3.pdf"

    echo "Running C++ SDK Example - SignXAdES"

    g++ -c -std=c++17 SignXAdES.cpp
    g++ -o SignXAdES.example SignXAdES.o -lpteidlib
    ./SignXAdES.example
    
    echo "Running C++ SDK Example - ChangePins"

    g++ -c -std=c++17 ChangePins.cpp
    g++ -o ChangePins.example ChangePins.o -lpteidlib 
    ./ChangePins.example -addr
    ./ChangePins.example -auth
    ./ChangePins.example -sign

fi
