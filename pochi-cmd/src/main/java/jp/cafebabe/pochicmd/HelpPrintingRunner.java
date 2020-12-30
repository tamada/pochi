package jp.cafebabe.pochicmd;

import java.io.IOException;

public class HelpPrintingRunner extends AbstractRunner {
    private Main main;

    public HelpPrintingRunner(Main main) {
        this.main = main;
    }

    @Override
    public void execute(Arguments args, Environment env) throws IOException {
        System.out.println(main.helpMessage());
    }

    @Override
    public Mode mode() {
        return Mode.HelpMode;
    }
}
