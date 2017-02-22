package com.github.ebis.scripts;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.github.ebis.config.Configuration;

public class ScriptRunnerBuilder {
    public static final String DEFAULT_SCRIPT_ENGINE_NAME = "JavaScript";

    private Configuration configuration;

    public ScriptRunnerBuilder(Configuration configuration){
        this.configuration = configuration;
    }

    public ScriptRunner build(Map<String, Object> properties){
        ScriptEngine engine = buildImpl(properties);
        
        return new ScriptRunner(engine, configuration);
    }

    private ScriptEngine buildImpl(Map<String, Object> properties){
        String[] args = (String[])properties.get("args");
        if(args.length > 0)
            return buildByFile(new File(args[0]), args);
        return buildByName((String)properties.getOrDefault("engine", DEFAULT_SCRIPT_ENGINE_NAME));
    }

    public ScriptEngine buildByName(String engineName){
        ScriptEngineManager manager = new ScriptEngineManager(configuration.classLoader());
        return manager.getEngineByName(engineName);
    }

    public ScriptEngine buildByFile(File scriptFile, String[] args){
        ScriptEngineManager manager = new ScriptEngineManager(configuration.classLoader());
        String ext = parseExtension(scriptFile.getName());
        return manager.getEngineByExtension(ext);
    }

    private String parseExtension(String name){
        int index = name.lastIndexOf('.');
        return name.substring(index + 1);
    }

    public ClassLoader createLoader(Optional<String> classpath){
        return createLoader(classpath, getClass().getClassLoader());
    }

    private ClassLoader createLoader(Optional<String> classpath, ClassLoader defaultLoader){
        Optional<ClassLoader> loader = classpath.map(cp -> new URLClassLoader(convertToUrls(cp), defaultLoader));
        return loader.orElse(defaultLoader);
    }

    private URL[] convertToUrls(String classpath){
        return Arrays.stream(classpath.split(":"))
                .map(path -> convertToUrl(Paths.get(path)))
                .filter(item -> item != null)
                .toArray(size -> new URL[size]);
    }

    private URL convertToUrl(Path path){
        try {
            return path.toUri().toURL();
        } catch (MalformedURLException e) {
        }
        return null;
    }
}
