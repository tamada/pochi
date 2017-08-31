extract = function(type, from){
    extractor = engine.extractor(type);
    source = engine.source(from)
    obj = {}
    obj.time = sys.measure(function(){
        obj.birthmarks = extractor.extract(source);
    })
    return obj;
}

compare = function(pair, compare, birthmarks){
    pair = engine.pairMatcher("RoundRobinWithSamePair")
    comparator = engine.comparator("JaccardIndex")
    obj = {};
    obj.time = sys.measure(function(){
        obj.comparisons = comparator.compare(birthmarks, pair);
    });
    return obj;
}

extractResult = extract("uc", "target/test-classes/resources/");
compareResult = compare("RoundRobinWithSamePair", "JaccardIndex", extractResult.birthmarks);

compareResult.comparisons.forEach(function(comparison){
    print(comparison);
});

fs.print("extraction: " + extractResult.time + " ns")
fs.print("comparison: " + compareResult.time + " ns")


