engine = {}

engine.source = function(location){
    return fs.open(location);
}
engine.version = function(){ 
    return sys.version();
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
engine.pairMatcher = function(type){
    return bmsys.pairMatcher(type, config);
}
engine.extractors = function(){
    return bmsys.extractors();
}
engine.comparators = function(){
    return bmsys.comparators();
}
engine.pairMatchers = function(){
    return bmsys.pairMatchers();
}
engine.rules = function(){
    return bmsys.rules();
}

