#!/bin/bash
# autenticacao.gov and docs.autenticacao.gov repositories clones have to be at same level
VERSION=$(awk -F "=" '/PTEID_PRODUCT_VERSION=/{print $2}' ../autenticacao.gov/pteid-mw-pt/_src/eidmw/release_data)
NOW=$(date +"%d/%m/%Y")

source illegal_chars.sh

#filter out header, table of contents and css class .center
echo "Removing header 'page', table of contents and css 'center' image class."
perl -p0e "s/.*Content_begin -->//s" user_manual.md > user_manual_no_header.md
perl -p0e "s/End_of_content.*//s" user_manual_no_header.md > user_manual_no_header_no_footer.md
sed 's/{:.center}//g' user_manual_no_header_no_footer.md > user_manual_filtered.md

#Strip any Unicode character above Latin-1 as our Latex font can't render them. Function defined in illegal_chars.sh
strip_unwanted_chars user_manual_filtered.md user_manual_clean_chars.md 

#generate pdf from ast
echo "Generating pdf manual."
pandoc -f gfm user_manual_clean_chars.md -o Manual_de_Utilizacao_v3.pdf --template=template.tex --pdf-engine=pdflatex --toc --number-sections --variable version=$VERSION --variable date=$NOW -V papersize:a4

if [ $? -eq 0 ]
then echo "Done. PDF file Manual_de_Utilizacao_v3.pdf created."
else
    echo "An error has ocurred. PDF file was not created."
fi

#clean-up
rm user_manual_no_header.md user_manual_no_header_no_footer.md user_manual_filtered.md user_manual_clean_chars.md
