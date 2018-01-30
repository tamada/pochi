extract = function(type, from){
    extractor = engine.extractor(type);
    source = engine.source(from)
    obj = {}
    obj.time = sys.measure(function(){
        obj.birthmarks = extractor.extract(source);
    })
    return obj;
}

result = extract("uc", "target/test-classes/resources");

result.birthmarks.forEach(function(birthmark){
    if(birthmark.className().matches(".*\\$.*")){
        print(birthmark);
    }
});

print(result.time + " ns");



