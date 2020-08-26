// extract UC birthmarks from given jar files.

// extract birthmarks by given extractor from given file path.
def extract(path, extractor) {
    printf("extract uc birthmark from %s%n", path)
    // obtains DataSource object for extracting birthmarks.
    source = pochi.source(path)
    // returns Birthmarks object contains Birthmark objects.
    return extractor.extract(source)
}

// prints each Birthmark object.
def printBirthmarks(birthmarks) {
    birthmarks.each(b -> println(b))
}

// gets UC birthmark extractor.
extractor = pochi.extractor("uc")

Arrays.stream(args)
    .map(file -> extract(file, extractor)) // converts to Birthmarks
    .each(birthmarks -> printBirthmarks(birthmarks)) // print given birthmarks.
