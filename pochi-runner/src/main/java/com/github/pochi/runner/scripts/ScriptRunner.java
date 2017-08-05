package com.github.pochi.runner.scripts;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.util.Optional;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.runner.scripts.helper.BirthmarkSystemHelper;
import com.github.pochi.runner.scripts.helper.IOHelper;
import com.github.pochi.runner.scripts.helper.SystemInfoHelper;
import com.github.pochi.runner.util.LogHelper;

public class ScriptRunner {
    public static final String DEFAULT_SCRIPT_ENGINE_NAME = "JavaScript";

    private ScriptEngine engine;

    public ScriptRunner(ScriptEngine engine, Configuration configuration){
        this.engine = engine;
        registerVariables(configuration);
        initialize(getClass().getResource("/js/initializer.js"));
    }

    private void registerVariables(Configuration configuration){
        engine.put("config", configuration);
        engine.put("fs",     new IOHelper());
        engine.put("sys",    new SystemInfoHelper());
        engine.put("bmsys",  new BirthmarkSystemHelper());
    }

    private void initialize(URL location) {
        try {
            engine.eval("load('" + location + "')");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public void perform(Reader in) throws IOException{
        try{
            engine.eval(in);
        } catch(ScriptException e){
            LogHelper.warn(this, e);
        }
    }

    public void oneLiner(String script) throws ScriptException{
        PrintWriter out = new PrintWriter(System.out);
        Optional<Object> object = Optional.ofNullable(engine.eval(script));
        object.ifPresent(out::println);
    }

    public void runsScript(String[] arguments) throws IOException, ScriptException{
        engine.put("argv", arguments);
        engine.put("filename", arguments[0]);
        try(Reader in = openReader(arguments[0])){
            engine.eval(in);
        }
    }

    private Reader openReader(String fileName) throws IOException{
        return new FileReader(fileName);
    }

    public void startInteraction() throws IOException{
        try{
            runInteractiveMode(buildLineReader(buildTerminal()));
        }
        catch(EndOfFileException e){
            // ignore exception, because it is finish of this application.
        }
    }

    private Terminal buildTerminal() throws IOException{
        return TerminalBuilder.builder()
                .system(true)
                .build();
    }

    private LineReader buildLineReader(Terminal terminal){
        return LineReaderBuilder.builder()
                .terminal(terminal)
                .appName("pochi")
                .completer(new JavaScriptKeywordsCompleter())
                .build();
    }

    private void runInteractiveMode(LineReader reader){
        PrintWriter out = reader.getTerminal().writer();
        out.printf("ScriptRunner start (%s)%n", engine.getFactory().getEngineName());

        String line;
        while((line = reader.readLine("pochi> ")) != null){
            try{
                Optional<Object> object = Optional.ofNullable(engine.eval(line));
                object.ifPresent(out::println);
            } catch(EndOfFileException e){
                throw e;
            } catch(Exception e){
                out.printf("%s: %s%n", e.getClass().getName(), e.getMessage());
            }
            out.flush();
        }
    }
}
