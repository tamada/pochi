package com.github.pochi.runner.config;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.pochi.runner.util.LogHelper;

public class Classpath implements Serializable{
    private static final long serialVersionUID = -2908802480835399355L;

    private String value;

    @JsonCreator
    public Classpath(String path){
        this.value = path;
    }

    public Optional<URL> toUrl(){
        try{
            return Optional.of(convertToUrl());
        }
        catch(MalformedURLException e){
            LogHelper.warn(this, e);
        }
        return Optional.empty();
    }

    private URL convertToUrl() throws MalformedURLException{
        return Paths.get(value)
                .toUri().toURL();        
    }

    @Override
    public boolean equals(Object object){
        return object instanceof Classpath
                && Objects.equals(
                        value, ((Classpath)object).value);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(value);
    }

    @Override
    public String toString(){
        return value;
    }
}
