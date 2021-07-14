package jp.cafebabe.pochicmd;

import java.io.IOException;

public class HelpPrintingRunner implements Runner {
    private Main main;

    public HelpPrintingRunner(Main main) {
        this.main = main;
    }

    @Override
    public final void run(Arguments args) {
        System.out.println(main.helpMessage());
    }

    @Override
    public Mode mode() {
        return Mode.HelpMode;
    }
}
