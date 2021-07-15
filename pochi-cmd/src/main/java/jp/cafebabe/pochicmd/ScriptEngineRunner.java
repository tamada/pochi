package jp.cafebabe.pochicmd;

import jp.cafebabe.pochi.BirthmarkSystemHelper;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Optional;

public abstract class ScriptEngineRunner implements Runner {
    private String name;

    public ScriptEngineRunner(String name) {
        this.name = name;
    }

    protected ScriptEngine load(Arguments args) throws ScriptException {
        ScriptEngine engine = loadImpl().orElseThrow(() -> new ScriptException(name + ": no ScriptEngine found"));
        engine.put("pochi", new BirthmarkSystemHelper(args.environment()));
        return engine;
    }

    protected Optional<ScriptEngine> loadImpl() {
        return Optional.ofNullable(new ScriptEngineManager()
                .getEngineByName(name));
    }

    protected final Reader toReader(String expression) {
        return new StringReader(expression);
    }
}
