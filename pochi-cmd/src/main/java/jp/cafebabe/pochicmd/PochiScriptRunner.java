package jp.cafebabe.pochicmd;

import io.vavr.control.Try;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class PochiScriptRunner extends ScriptEngineRunner {
    public PochiScriptRunner() {
        super("groovy");
    }

    @Override
    public void run(Arguments args) throws IOException {
        Try.of(() -> load(args))
                .andThenTry(engine -> eval(args, engine))
                .getOrElseThrow(throwable -> new IOException(throwable));
    }

    private void eval(Arguments args, ScriptEngine engine) throws IOException, ScriptException {
        Path script = putValues(engine, args);
        try(Reader in = Files.newBufferedReader(script)) {
            engine.eval(in);
        }
    }

    private Path putValues(ScriptEngine engine, Arguments args) {
        engine.put("args", args.arguments()
                .skip(1).collect(Collectors.toList()));
        Path script = Path.of(args.arguments().findFirst().get());
        engine.put("script", script);
        return script;
    }

    @Override
    public Mode mode() {
        return Mode.ScriptFile;
    }
}
