parse = function(from){
    parser = engine.parser()
    source = engine.source(from);
    obj = {};
    obj.time = sys.measure(function(){
        obj.birthmarks = parser.parse(source);
    });
    return obj;
}

parseResult = parse("target/test-classes/resources/commons-cli-1.1-uc.csv")

parseResult.birthmarks.forEach(function(birthmark){
    print(birthmark);
});

print("read: " + parseResult.time + " ns")
