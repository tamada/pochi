package jp.cafebabe.pochicmd;

import io.vavr.control.Try;

import java.io.IOException;

public class ExpressionRunner extends ScriptEngineRunner {
    public ExpressionRunner() {
        super("groovy");
    }

    @Override
    public void run(Arguments args) throws IOException {
        Try.of(() -> load(args))
                .mapTry(engine -> engine.eval(toReader(args.expression())))
                .getOrElseThrow(throwable -> new IOException(throwable));
    }

    @Override
    public Mode mode() {
        return Mode.OneLineExpression;
    }
}
