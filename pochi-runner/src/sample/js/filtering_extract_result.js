extractor = bmsys.extractor("uc");
source = fs.open("target/test-classes/resources/");
birthmarks = extractor.extract(source);

birthmarks2 = birthmarks.filter(function(birthmark){
    return birthmark.className().toString().startsWith("org.apache")
});

fs.print(birthmarks2);

fs.print("extraction: " + birthmarks.time() + " ns")
fs.print("filtering: " + birthmarks2.time() + " ns")


