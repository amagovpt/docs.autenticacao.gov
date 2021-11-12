#!/bin/bash

#You can and should change these paths according to your project and library locations
JAR_PATH="/usr/local/lib/pteid_jni/pteidlibj.jar"
LIB_PATH="/usr/local/lib/"


#Possible options for example programs () : "-identity", "-addr", "-auth", "-sign", "-all"
#Only -identity can be used with no PIN prompts

OPTION=$1

if [ -z $OPTION ]; then
    echo "Usage: ./run_all.sh [-identity/-addr/-auth/-sign/-all]"
    exit 0
fi

if [ $OPTION == "-identity" ]; then

    echo "Running Java SDK Example - ReadCard"

    javac -cp $JAR_PATH:. ReadCard.java
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. ReadCard

    echo "Running Java SDK Example - GetPhoto"

    javac -cp $JAR_PATH:. GetPhoto.java
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. GetPhoto "-png" "files/image.png"
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. GetPhoto "-jp2" "files/image.jp2"

    echo "Running Java SDK Example - PinInfo"

    javac -cp $JAR_PATH:. PinInfo.java
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. PinInfo

elif [ $OPTION == "-addr" ]; then

    echo "Running Java SDK Example - ReadAddress"

    javac -cp $JAR_PATH:. ReadAddress.java
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. ReadAddress

    echo "Running Java SDK Example - GetXML"

    javac -cp $JAR_PATH:. GetXML.java
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. GetXML

elif [ $OPTION == "-auth" ]; then

    echo "Running Java SDK Example - ReadAndWriteNotes"

    javac -cp $JAR_PATH:. ReadAndWriteNotes.java
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. ReadAndWriteNotes


elif [ $OPTION == "-sign" ]; then

    echo "Running Java SDK Example - SignPDFDocument"

    javac -cp $JAR_PATH:. SignPDFDocument.java
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. SignPDFDocument "files/input.pdf" "files/output.pdf"

    echo "Running Java SDK Example - SignPDFMultipleFiles (2 documents)"

    javac -cp $JAR_PATH:. SignPDFMultipleFiles.java
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. SignPDFMultipleFiles "files/" "files/input1.pdf" "files/input2.pdf"

    echo "Running Java SDK Example - SignPDFCustomSize"

    javac -cp $JAR_PATH:. SignPDFCustomSize.java
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. SignPDFCustomSize "files/input.pdf" "files/output_custom.pdf"

	echo "Running Java SDK Example - SignData"
	javac -cp $JAR_PATH:. SignData.java
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. SignData

elif [ $OPTION == "-signCMD" ]; then

    echo "Running Java SDK Example - SignFileCMD"
    javac -cp $JAR_PATH:. SignFileCMD.java
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. SignFileCMD "files/input.pdf" "files/output.pdf"


    echo "Running Java SDK Example - SignFileSigningDevice"
    javac -cp $JAR_PATH:. SignFileSigningDevice.java
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. SignFileSigningDevice -BOTH "files/input.pdf" "files/output.pdf"

elif [ $OPTION == "-all" ]; then

    echo "Running Java SDK Example - ReadCard"

    javac -cp $JAR_PATH:. ReadCard.java
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. ReadCard

    echo "Running Java SDK Example - GetPhoto"

    javac -cp $JAR_PATH:. GetPhoto.java
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. GetPhoto "-png" "files/image.png"
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. GetPhoto "-jp2" "files/image.jp2"

    echo "Running Java SDK Example - PinInfo"

    javac -cp $JAR_PATH:. PinInfo.java
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. PinInfo
    
    echo "Running Java SDK Example - ReadAddress"

    javac -cp $JAR_PATH:. ReadAddress.java
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. ReadAddress

    echo "Running Java SDK Example - GetXML"

    javac -cp $JAR_PATH:. GetXML.java
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. GetXML
    echo "Running Java SDK Example - ReadAndWriteNotes"

    javac -cp $JAR_PATH:. ReadAndWriteNotes.java
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. ReadAndWriteNotes

    echo "Running Java SDK Example - SignPDFDocument"

    javac -cp $JAR_PATH:. SignPDFDocument.java
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. SignPDFDocument "files/input.pdf" "files/output.pdf"

    echo "Running Java SDK Example - SignPDFMultipleFiles"

    javac -cp $JAR_PATH:. SignPDFMultipleFiles.java
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. SignPDFMultipleFiles "files/" "files/input1.pdf" "files/input2.pdf"

    echo "Running Java SDK Example - SignXAdES"

    javac -cp $JAR_PATH:. SignXAdES.java
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. SignXAdES

    echo "Running Java SDK Example - ChangePins"

    javac -cp $JAR_PATH:. ChangePins.java
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. ChangePins "-addr"
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. ChangePins "-auth"
    java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. ChangePins "-sign"

fi
