extract = function(type, from){
    extractor = engine.extractor(type);
    source = engine.source(from)
    obj = {}
    callbackOnExtractionError = function(entry, exception){
        // some operation for logging error sources.
    }
    obj.time = sys.measure(function(){
        // obj.birthmarks = extractor.extract(source); // no call back function is OK!
        obj.birthmarks = extractor.extract(source, callbackOnExtractionError);
    })
    return obj;
}

result = extract("uc", "target/test-classes/resources");

result.birthmarks.forEach(function(birthmark){
    print(birthmark);
});

print(result.time + " ns");
