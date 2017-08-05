engine = {}

engine.source = function(location){
    return fs.open(location);
}
engine.version = function(){ 
    print(sys.version());
}

engine.extractor = function(type){
    return bmsys.extractor(type, config);
}
engine.comparator = function(type){
    return bmsys.comparator(type, config);
}
engine.parser = function(type){
    return bmsys.parser(config);
}
engine.pairMaker = function(type){
    return bmsys.pairMaker(type, config);
}
engine.extractors = function(){
    return bmsys.extractors();
}
engine.comparators = function(){
    return bmsys.comparators();
}
engine.pairMakers = function(){
    return bmsys.pairMakers();
}

