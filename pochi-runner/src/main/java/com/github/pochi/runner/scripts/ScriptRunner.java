package com.github.pochi.runner.scripts;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
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
    private Configuration configuration;

    public ScriptRunner(ScriptEngine engine, Configuration configuration){
        this.engine = engine;
        this.configuration = configuration;
        registerVariables();
    }

    private void registerVariables(){
        engine.put("config", configuration);
        registerHelpers(configuration);
    }

    private void registerHelpers(Configuration configuration){
        engine.put("fs", new IOHelper());
        engine.put("sys", new SystemInfoHelper());
        engine.put("bmsys", new BirthmarkSystemHelper(configuration));
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
        Optional<Object> object = Optional.of(engine.eval(script));
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
            LogHelper.warn(this, e);
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
                Object object = engine.eval(line);
                out.println(object);
            } catch(EndOfFileException e){
                throw e;
            } catch(Exception e){
                out.printf("%s: %s%n", e.getClass().getName(), e.getMessage());
            }
            out.flush();
        }
    }
}
