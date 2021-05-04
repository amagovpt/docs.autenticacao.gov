#!/bin/bash

echo "Running C++ SDK Example - ReadCard"

g++ -c -std=c++17 -g -O0 -I/eidlib/ -fno-omit-frame-pointer ReadCard.cpp
g++ -o ReadCard.example ReadCard.o -lpteidlib -lpteidapplayer -lcrypto -lrt -Wl,-rpath-link=/lib -L/lib
./ReadCard.example

echo "Running C++ SDK Example - GetPhoto"

g++ -c -std=c++17 -g -O0 -I/eidlib/ -fno-omit-frame-pointer GetPhoto.cpp
g++ -o GetPhoto.example GetPhoto.o -lpteidlib -lpteidapplayer -lcrypto -lrt -Wl,-rpath-link=/lib -L/lib
./GetPhoto.example -png "files/image.png"
./GetPhoto.example -jp2 "files/image.jp2"

echo "Running C++ SDK Example - ReadAddress"

g++ -c -std=c++17 -g -O0 -I/eidlib/ -fno-omit-frame-pointer ReadAddress.cpp
g++ -o ReadAddress.example ReadAddress.o -lpteidlib -lpteidapplayer -lcrypto -lrt -Wl,-rpath-link=/lib -L/lib
./ReadAddress.example

echo "Running C++ SDK Example - GetXML"

g++ -c -std=c++17 -g -O0 -I/eidlib/ -fno-omit-frame-pointer GetXML.cpp
g++ -o GetXML.example GetXML.o -lpteidlib -lpteidapplayer -lcrypto -lrt -Wl,-rpath-link=/lib -L/lib
./GetXML.example

echo "Running C++ SDK Example - ReadAndWriteNotes"

g++ -c -std=c++17 -g -O0 -I/eidlib/ -fno-omit-frame-pointer ReadAndWriteNotes.cpp
g++ -o ReadAndWriteNotes.example ReadAndWriteNotes.o -lpteidlib -lpteidapplayer -lcrypto -lrt -Wl,-rpath-link=/lib -L/lib
./ReadAndWriteNotes.example

echo "Running C++ SDK Example - SignFile"

g++ -c -std=c++17 -g -O0 -I/eidlib/ -fno-omit-frame-pointer SignFile.cpp
g++ -o SignFile.example SignFile.o -lpteidlib -lpteidapplayer -lcrypto -lrt -Wl,-rpath-link=/lib -L/lib
./SignFile.example "files/input.pdf" "files/output.pdf"

echo "Running C++ SDK Example - SignMultipleFiles"

g++ -c -std=c++17 -g -O0 -I/eidlib/ -fno-omit-frame-pointer SignMultipleFiles.cpp
g++ -o SignMultipleFiles.example SignMultipleFiles.o -lpteidlib -lpteidapplayer -lcrypto -lrt -Wl,-rpath-link=/lib -L/lib
./SignMultipleFiles.example "files/" "files/input1.pdf" "files/input2.pdf" "files/input3.pdf"

echo "Running C++ SDK Example - ChangePins"

g++ -c -std=c++17 -g -O0 -I/eidlib/ -fno-omit-frame-pointer ChangePins.cpp
g++ -o ChangePins.example ChangePins.o -lpteidlib -lpteidapplayer -lcrypto -lrt -Wl,-rpath-link=/lib -L/lib
./ChangePins.example -addr
./ChangePins.example -auth
./ChangePins.example -sign
