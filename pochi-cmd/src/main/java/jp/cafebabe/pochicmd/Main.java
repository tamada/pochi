package jp.cafebabe.pochicmd;

public class Main {
    public static final String VERSION = "2.3.1";
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

    public static void main(String[] args) throws Exception {
        new Main(args);
    }
}
