parser = bmsys.parser()
source = fs.open("target/test-classes/resources/commons-cli-1.1-uc.csv");
result = parser.parse(source);

fs.print(result);
