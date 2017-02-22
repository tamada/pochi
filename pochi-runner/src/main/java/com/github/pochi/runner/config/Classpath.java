package com.github.pochi.runner.config;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Classpath implements Serializable{
    private static final long serialVersionUID = -2908802480835399355L;

    private String classpath;

    @JsonCreator
    public Classpath(String path){
        this.classpath = path;
    }

    public Optional<URL> toUrl(){
        try{ return Optional.of(convertToUrl()); }
        catch(MalformedURLException e){ }
        return Optional.empty();
    }

    private URL convertToUrl() throws MalformedURLException{
        return Paths.get(classpath)
                .toUri().toURL();        
    }

    @Override
    public boolean equals(Object object){
        return object instanceof Classpath
                && Objects.equals(
                        classpath, ((Classpath)object).classpath);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(classpath);
    }

    @Override
    public String toString(){
        return classpath;
    }
}
