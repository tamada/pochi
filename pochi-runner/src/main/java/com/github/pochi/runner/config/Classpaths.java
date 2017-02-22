package com.github.pochi.runner.config;

import java.io.Serializable;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Classpaths implements Serializable{
    private static final long serialVersionUID = -3046402976706943359L;

    @JsonProperty("classpaths")
    private List<Classpath> classpaths = new ArrayList<>();

    public void forEach(Consumer<Classpath> consumer){
        stream().forEach(consumer);
    }

    public void add(Classpath classpath){
        classpaths.add(classpath);
    }

    public Classpath[] toArray(){
        return classpaths.stream()
                .toArray(size -> new Classpath[size]);
    }

    public ClassLoader buildClassLoader(){
        return buildClassLoader(getClass().getClassLoader());
    }

    public ClassLoader buildClassLoader(ClassLoader parent){
        return new URLClassLoader(toUrls(), parent);
    }

    private Stream<Classpath> stream(){
        return classpaths.stream();
    }

    private URL[] toUrls(){
        return stream().map(classpath -> classpath.toUrl())
                .filter(optional -> optional.isPresent())
                .map(optional -> optional.get())
                .toArray(size -> new URL[size]);
    }
}
