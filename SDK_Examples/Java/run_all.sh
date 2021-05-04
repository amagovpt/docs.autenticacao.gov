#!/bin/bash

#You can and should change these paths according to your project and library locations
JAR_PATH="pteidlibj.jar"
LIB_PATH="/usr/local/lib/"

echo "Running Java SDK Example - ReadCard"

javac -cp $JAR_PATH:. ReadCard.java
java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. ReadCard

echo "Running Java SDK Example - GetPhoto"

javac -cp $JAR_PATH:. GetPhoto.java
java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. GetPhoto "files/image.png"

echo "Running Java SDK Example - ReadAddress"

javac -cp $JAR_PATH:. ReadAddress.java
java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. ReadAddress

echo "Running Java SDK Example - GetXML"

javac -cp $JAR_PATH:. GetXML.java
java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. GetXML 

echo "Running Java SDK Example - ReadAndWriteNotes"

javac -cp $JAR_PATH:. ReadAndWriteNotes.java
java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. ReadAndWriteNotes 


echo "Running Java SDK Example - SignFile"

javac -cp $JAR_PATH:. SignFile.java
java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. SignFile "files/input.pdf" "files/output.pdf"

echo "Running Java SDK Example - SignMultipleFiles"

javac -cp $JAR_PATH:. SignMultipleFiles.java
java -Djava.library.path=$LIB_PATH -Dfile.encoding=utf8 -cp $JAR_PATH:. SignMultipleFiles "files/" "files/input1.pdf" "files/input2.pdf" 