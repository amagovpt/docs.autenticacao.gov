#!/bin/bash
#Possible options for sets of example programs: "-identity", "-addr", "-auth", "-sign", "-signCMD", "-all"
#Only -identity can be used without PIN prompts

#Java 22 introduced new native library warnings
java_is_at_least_22() {
    command -v java >/dev/null 2>&1 || return 1

    local version major
    version=$(java -version 2>&1 | sed -n '1s/.*version "\([^"]*\)".*/\1/p')

    if [[ "$version" == 1.* ]]; then
        major=${version#1.}
        major=${major%%.*}
    else
        major=${version%%.*}
    fi

    [[ "$major" =~ ^[0-9]+$ ]] && [ "$major" -ge 22 ]
}

#You can and should change these paths according to your project and library locations
JAR_PATH="/usr/local/lib/pteid_jni/pteidlibj.jar"
LIB_PATH="/usr/local/lib/"

PHOTO_PNG="files/citizen_photo.png"
PHOTO_JPEG2000="files/citizen_photo.jp2"

JVM_ARGS="-Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:."

if java_is_at_least_22; then
    JVM_ARGS="$JVM_ARGS --enable-native-access=ALL-UNNAMED"
fi

OPTION=$1
if [ -z $OPTION ]; then
    echo "Usage: ./run_all.sh [-identity/-addr/-auth/-sign/-signCMD/-all]"
    echo "-identity is the only option which won't prompt for card PINs"
    exit 0
fi

if [ $OPTION == "-identity" ]; then

    echo "Running Java SDK Example - ReadCard"

    javac -cp $JAR_PATH:. ReadCard.java
    java $JVM_ARGS ReadCard

    echo "Running Java SDK Example - TestMultipass"
    javac -cp $JAR_PATH:. TestMultipass.java
    java $JVM_ARGS TestMultipass

    echo "Running Java SDK Example - ListCertificates"

    javac -cp $JAR_PATH:. ListCertificates.java
    java $JVM_ARGS ListCertificates TEST

    echo "Running Java SDK Example - GetPhoto"

    javac -cp $JAR_PATH:. GetPhoto.java

    echo "1st run of GetPhoto with PNG output..."
    java $JVM_ARGS GetPhoto "-png" $PHOTO_PNG

    echo "2nd run of GetPhoto with JP2 output..."
    java $JVM_ARGS GetPhoto "-jp2" $PHOTO_JPEG2000

    echo "Running Java SDK Example - PinInfo"

    javac -cp $JAR_PATH:. PinInfo.java
    java $JVM_ARGS PinInfo

elif [ $OPTION == "-addr" ]; then

    echo "Running Java SDK Example - ReadAddress"

    javac -cp $JAR_PATH:. ReadAddress.java
    java $JVM_ARGS ReadAddress

    echo "Running Java SDK Example - GetXML"

    javac -cp $JAR_PATH:. GetXML.java
    java $JVM_ARGS GetXML

elif [ $OPTION == "-auth" ]; then

    echo "Running Java SDK Example - ReadAndWriteNotes"

    javac -cp $JAR_PATH:. ReadAndWriteNotes.java
    java $JVM_ARGS ReadAndWriteNotes

elif [ $OPTION == "-sign" ]; then

    echo "Running Java SDK Example - SignPDFDocument"

    javac -cp $JAR_PATH:. SignPDFDocument.java
    java $JVM_ARGS SignPDFDocument "files/input.pdf" "files/output.pdf"

    echo "Running Java SDK Example - SignPDFMultipleFiles (2 documents)"

    javac -cp $JAR_PATH:. SignPDFMultipleFiles.java
    java $JVM_ARGS SignPDFMultipleFiles "files/" "files/input1.pdf" "files/input2.pdf"

    echo "Running Java SDK Example - SignPDFCustomSize"

    javac -cp $JAR_PATH:. SignPDFCustomSize.java
    java $JVM_ARGS SignPDFCustomSize "files/input.pdf" "files/output_custom.pdf"

	echo "Running Java SDK Example - SignData"
	javac -cp $JAR_PATH:. SignData.java
    java $JVM_ARGS SignData

elif [ $OPTION == "-signCMD" ]; then

    echo "Running Java SDK Example - SignFileCMD"
    javac -cp $JAR_PATH:. SignFileCMD.java
    java $JVM_ARGS SignFileCMD "files/input.pdf" "files/output.pdf"


    echo "Running Java SDK Example - SignFileSigningDevice"
    javac -cp $JAR_PATH:. SignFileSigningDevice.java
    java $JVM_ARGS SignFileSigningDevice -BOTH "files/input.pdf" "files/output.pdf"

elif [ $OPTION == "-all" ]; then

    echo "Running Java SDK Example - ReadCard"

    javac -cp $JAR_PATH:. ReadCard.java
    java $JVM_ARGS ReadCard

    echo "Running Java SDK Example - GetPhoto"

    javac -cp $JAR_PATH:. GetPhoto.java
    java $JVM_ARGS GetPhoto "-png" $PHOTO_PNG
    java $JVM_ARGS GetPhoto "-jp2" $PHOTO_JPEG2000

    echo "Running Java SDK Example - PinInfo"

    javac -cp $JAR_PATH:. PinInfo.java
    java $JVM_ARGS PinInfo
    
    echo "Running Java SDK Example - ReadAddress"

    javac -cp $JAR_PATH:. ReadAddress.java
    java $JVM_ARGS ReadAddress

    echo "Running Java SDK Example - GetXML"

    javac -cp $JAR_PATH:. GetXML.java
    java $JVM_ARGS GetXML
    echo "Running Java SDK Example - ReadAndWriteNotes"

    javac -cp $JAR_PATH:. ReadAndWriteNotes.java
    java $JVM_ARGS ReadAndWriteNotes

    echo "Running Java SDK Example - SignPDFDocument"

    javac -cp $JAR_PATH:. SignPDFDocument.java
    java $JVM_ARGS SignPDFDocument "files/input.pdf" "files/output.pdf"

    echo "Running Java SDK Example - SignPDFMultipleFiles"

    javac -cp $JAR_PATH:. SignPDFMultipleFiles.java
    java $JVM_ARGS SignPDFMultipleFiles "files/" "files/input1.pdf" "files/input2.pdf"

    echo "Running Java SDK Example - SignXAdES"

    javac -cp $JAR_PATH:. SignXAdES.java
    java $JVM_ARGS SignXAdES

    echo "Running Java SDK Example - ChangePins"

    javac -cp $JAR_PATH:. ChangePins.java
    java $JVM_ARGS ChangePins "-addr"
    java $JVM_ARGS ChangePins "-auth"
    java $JVM_ARGS ChangePins "-sign"

fi
