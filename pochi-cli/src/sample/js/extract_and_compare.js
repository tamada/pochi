extract = function(type, from){
    extractor = engine.extractor(type);
    source = engine.source(from)
    obj = {}
    obj.time = sys.measure(function(){
        obj.birthmarks = extractor.extract(source);
    })
    return obj;
}

compare = function(pairingAlgorithm, comparingAlgorithm, birthmarks){
    pair = engine.pairMatcher(pairingAlgorithm)
    comparator = engine.comparator(comparingAlgorithm)
    obj = {};
    obj.time = sys.measure(function(){
        obj.comparisons = comparator.compare(birthmarks, pair);
    });
    return obj;
}

extractResult = extract("2-gram", argv[1]);
compareResult = compare("RoundRobinWithSamePair", "JaccardIndex", extractResult.birthmarks);

compareResult.comparisons.forEach(function(comparison){
    print(comparison);
});

fs.print("extraction: " + extractResult.time + " ns")
fs.print("comparison: " + compareResult.time + " ns")


