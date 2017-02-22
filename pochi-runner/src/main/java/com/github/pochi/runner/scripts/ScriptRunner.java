package com.github.pochi.runner.scripts;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import com.github.pochi.runner.config.Configuration;
import com.github.pochi.runner.scripts.helper.BirthmarkSystemHelper;
import com.github.pochi.runner.scripts.helper.IOHelper;
import com.github.pochi.runner.scripts.helper.SystemInfoHelper;

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
            e.printStackTrace();
        }
    }

    public void oneLiner(String script) throws ScriptException{
        PrintWriter out = new PrintWriter(System.out);
        Object object = engine.eval(script);
        out.println(object);
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
        try{ runInteractiveMode(buildLineReader(buildTerminal())); }
        catch(EndOfFileException e){ }
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
                .completer(new EbisCompleter())
                .build();
    }

    private void runInteractiveMode(LineReader reader){
        PrintWriter out = new PrintWriter(System.out);
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
