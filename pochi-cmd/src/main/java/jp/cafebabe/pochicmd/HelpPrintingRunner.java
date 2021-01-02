package jp.cafebabe.pochicmd;

import java.io.IOException;

public class HelpPrintingRunner extends AbstractRunner {
    private Main main;

    public HelpPrintingRunner(Main main) {
        this.main = main;
    }

    @Override
    public String targetName() {
        return null;
    }

    @Override
    public final void execute(Arguments args) throws IOException {
        System.out.println(main.helpMessage());
    }

    @Override
    public Mode mode() {
        return Mode.HelpMode;
    }
}
