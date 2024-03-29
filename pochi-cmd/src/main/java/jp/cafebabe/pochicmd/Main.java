package jp.cafebabe.pochicmd;

import jp.cafebabe.pochi.Pochi;

public class Main {
    public static final String VERSION = Pochi.VERSION;
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
                return new HelpPrintingRunner();
        }
    }

    public static void main(String[] args) throws Exception {
        new Main(args);
    }
}
