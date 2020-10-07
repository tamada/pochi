// compare extracted birthmarks from given jar files.

import jp.cafebabe.birthmarks.comparators.Threshold;
import jp.cafebabe.birthmarks.entities.Birthmarks;

// extract birthmarks by given extractor from given file path.
def extract(path, extractor) {
    source = pochi.source(path)
    return extractor.extract(source)
}

// gets UC birthmark extractor.
extractor = pochi.extractor("uc")

birthmarks = Arrays.stream(args)
    .map(file -> extract(file, extractor))              // converts to Birthmarks
    .reduce(new Birthmarks(), (b1, b2) -> b1.merge(b2)) // merges to one Birthmarks object

comparator = pochi.comparator("JaccardIndex")
matcher = pochi.matcher("RoundRobin")

// default threshold (0.75)
threshold = Threshold.DEFAULT

matcher.match(birthmarks)
    .map(pair -> comparator.compare(pair))
    .filter(either -> either.isRight())
    .map(either -> either.get())
    .filter(comparison -> comparison.isStolen(threshold))
    .forEach(comparison -> println(comparison))
