extractor = bmsys.extractor("uc");
source = fs.open("target/test-classes/resources/");
birthmarks = extractor.extract(source);

fs.print(birthmarks);

fs.print("extraction: " + birthmarks.time() + " ns")


