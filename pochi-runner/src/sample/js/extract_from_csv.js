parser = bmsys.parser()
source = fs.open("target/test-classes/resources/commons-cli-1.1-uc.csv");
birthmarks = parser.parse(source);

fs.print(birthmarks);

MILLI_SECONDS = com.github.pochi.runner.util.Unit.MILLI_SECONDS;
fs.print("time: " + birthmarks.time(MILLI_SECONDS) + " ms")
