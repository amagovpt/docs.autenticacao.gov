
function strip_unwanted_chars {
    echo "strip_unwanted_chars: operating on $1..."
    #All characters above the latin-1 unicode section are unwanted
    cat $1 | perl -CS -pe 's/[\x{100}-\x{1FBFF}]+//g' > $2
}
