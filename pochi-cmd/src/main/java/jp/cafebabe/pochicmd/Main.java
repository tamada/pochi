package jp.cafebabe.pochicmd;

import java.util.Optional;

public class Main {
    public static final String VERSION = "2.2.0";
    public static final String CONFIG_NAME = "POCHI_CONFIG_PATH";

    public Main(String[] args) throws Exception {
        Arguments arguments = Arguments.parse(args);
        Runner runner = createRunner(arguments.findMode());
        runner.run(arguments);
    }

    private Runner createRunner(Runner.Mode mode) {
        switch(mode){
            case Interactive:
                return new InteractiveRunner();
            case ScriptFile:
                return new PochiScriptRunner();
            case OneLineExpression:
                return new ExpressionRunner();
            default:
                return new HelpPrintingRunner(this);
        }
    }

    public String helpMessage() {
        return String.format("pochi version %s%n" +
                "pochi [OPTIONS] [SCRIPT FILE [ARGV...]]%n" +
                "OPTIONS%n" +
                "    -c, --classpath <CLASSPATH>      specifies the classpaths for Groovy (JVM) separated with colon (:).%n" +
                "    -C, --config <CONFIG_FILE>       specifies the configuration file.%n" +
                "    -e, --expression <EXPRESSION>    specifies one line script.%n" +
                "    -w, --working-dir <DIR>          specifies the working directory.%n" +
                "    -v, --verbose                    sets as verbose mode.%n%n" +
                "    -h, --help                       prints this message.%n" +
                "SCRIPT_FILE [ARGV...]%n" +
                "    Groovy script file and its arguments.%n" +
                "    If no script files and no expression were given, pochi runs on interactive mode.",
                VERSION);
    }

    public static void main(String[] args) throws Exception {
        new Main(args);
    }
}
