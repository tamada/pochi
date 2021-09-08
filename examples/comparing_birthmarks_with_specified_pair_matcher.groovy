// compare extracted birthmarks from given jar files with the specified_pair_matcher.

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

pochi.config().put("pair.list", "examples/sample_matching.csv") // specified pairs by csv file with the key "pair.list".
comparator = pochi.comparator("JaccardIndex")
matcher = pochi.matcher("Specified")

// default threshold (0.75)
threshold = Threshold.DEFAULT

matcher.match(birthmarks)
    .map(pair -> comparator.compare(pair))
    .filter(either -> either.isRight())
    .map(either -> either.get())
    // .filter(comparison -> comparison.isStolen(threshold)) //  no filtering
    .forEach(comparison -> println(comparison))
