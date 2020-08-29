def contains(entry) {
    return entry.isClass()
        && !entry.className().contains("pochi")
}

def filter(source) {
    // filtering DataSource entry by contains method.
    return source.filter(entry -> contains(entry))
}

def printSource(source) {
    source.stream()
        .each(entry -> println(entry.loadFrom()))
}

Arrays.stream(args)
    .map(arg -> pochi.source(arg))
    .map(source -> filter(source))
    .each(source -> printSource(source))
