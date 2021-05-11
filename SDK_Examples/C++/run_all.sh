#!/bin/bash

TYPE=$1

#You can pass to the script the following flags: "-simple", "-addr", "-auth", "-sign", "-all"
if [ $TYPE = "-simple" ]; then

    echo "Running C++ SDK Example - ReadCard"

    g++ -c -std=c++17 ReadCard.cpp
    g++ -o ReadCard.example ReadCard.o -lpteidlib 
    ./ReadCard.example

    echo "Running C++ SDK Example - GetPhoto"

    g++ -c -std=c++17 GetPhoto.cpp
    g++ -o GetPhoto.example GetPhoto.o -lpteidlib
    ./GetPhoto.example -png "files/image.png"
    ./GetPhoto.example -jp2 "files/image.jp2"

elif [ $TYPE == "-addr" ]; then

    echo "Running C++ SDK Example - ReadAddress"

    g++ -c -std=c++17 ReadAddress.cpp
    g++ -o ReadAddress.example ReadAddress.o -lpteidlib 
    ./ReadAddress.example

    echo "Running C++ SDK Example - GetXML"

    g++ -c -std=c++17 GetXML.cpp
    g++ -o GetXML.example GetXML.o -lpteidlib 
    ./GetXML.example

elif [ $TYPE == "-auth" ]; then

    echo "Running C++ SDK Example - ReadAndWriteNotes"

    g++ -c -std=c++17 ReadAndWriteNotes.cpp
    g++ -o ReadAndWriteNotes.example ReadAndWriteNotes.o -lpteidlib
    ./ReadAndWriteNotes.example

elif [ $TYPE == "-sign" ]; then

    echo "Running C++ SDK Example - SignFile"

    g++ -c -std=c++17 SignFile.cpp
    g++ -o SignFile.example SignFile.o -lpteidlib 
    ./SignFile.example "files/input.pdf" "files/output.pdf"

    echo "Running C++ SDK Example - SignMultipleFiles"

    g++ -c -std=c++17 SignMultipleFiles.cpp
    g++ -o SignMultipleFiles.example SignMultipleFiles.o -lpteidlib
    ./SignMultipleFiles.example "files/" "files/input1.pdf" "files/input2.pdf" "files/input3.pdf"

    echo "Running C++ SDK Example - SignXAdES"

    g++ -c -std=c++17 SignXAdES.cpp
    g++ -o SignXAdES.example SignXAdES.o -lpteidlib
    ./SignXAdES.example

elif [ $TYPE == "-all" ]; then

    echo "Running C++ SDK Example - ReadCard"

    g++ -c -std=c++17 ReadCard.cpp
    g++ -o ReadCard.example ReadCard.o -lpteidlib 
    ./ReadCard.example

    echo "Running C++ SDK Example - GetPhoto"

    g++ -c -std=c++17 GetPhoto.cpp
    g++ -o GetPhoto.example GetPhoto.o -lpteidlib
    ./GetPhoto.example -png "files/image.png"
    ./GetPhoto.example -jp2 "files/image.jp2"

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

    echo "Running C++ SDK Example - SignFile"

    g++ -c -std=c++17 SignFile.cpp
    g++ -o SignFile.example SignFile.o -lpteidlib 
    ./SignFile.example "files/input.pdf" "files/output.pdf"

    echo "Running C++ SDK Example - SignMultipleFiles"

    g++ -c -std=c++17 SignMultipleFiles.cpp
    g++ -o SignMultipleFiles.example SignMultipleFiles.o -lpteidlib
    ./SignMultipleFiles.example "files/" "files/input1.pdf" "files/input2.pdf" "files/input3.pdf"

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
