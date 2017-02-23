extractor = bmsys.extractor("uc");
source = fs.open("target/test-classes/resources/");
birthmarks = extractor.extract(source);

pair2 = bmsys.pairMaker("RoundRobinWithSamePair")
comparator = bmsys.comparator("JaccardIndex")
comparisons = comparator.compare(birthmarks, pair2);

threshold = bmsys.threshold(0.25);
comparisons2 = comparisons.filter(function(comparison){
    return comparison.isInconclusive(threshold)
});
fs.print(comparisons2);

fs.print("extraction: " + birthmarks.time() + " ns")
fs.print("comparison: " + comparisons.time() + " ns")
fs.print("filter: " + comparisons2.time() + " ns")


