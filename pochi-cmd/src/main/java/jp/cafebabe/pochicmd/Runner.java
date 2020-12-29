package jp.cafebabe.pochicmd;

import java.io.IOException;

public interface Runner {
    public static enum Mode {
        Interactive,
        OneLineExpression,
        ScriptFile,
        HelpMode,
    }

    void run(Arguments args) throws IOException;

    Mode mode();
}
