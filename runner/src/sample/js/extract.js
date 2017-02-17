extractor = bmsys.extractor("uc");
source = fs.open("target/test-classes/resources/");
result = extractor.extract(source);

pair2 = bmsys.pairMaker("RoundRobinWithSamePair")
comparator = bmsys.comparator("JaccardIndex")
result2 = comparator.compare(result, pair2);
fs.print(result2);

fs.print("extraction: " + result.result().time() + " ns")
fs.print("comparison: " + result2.result().time() + " ns")


