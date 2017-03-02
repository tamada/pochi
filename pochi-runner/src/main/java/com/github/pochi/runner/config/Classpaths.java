package com.github.pochi.runner.config;

import java.io.Serializable;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Classpaths implements Serializable{
    private static final long serialVersionUID = -3046402976706943359L;

    @JsonProperty("classpaths")
    private List<Classpath> list = new ArrayList<>();

    public void forEach(Consumer<Classpath> consumer){
        stream().forEach(consumer);
    }

    public void add(Classpath classpath){
        list.add(classpath);
    }

    public Classpath[] toArray(){
        return list.stream()
                .toArray(Classpath[]::new);
    }

    public ClassLoader buildClassLoader(){
        return buildClassLoader(getClass().getClassLoader());
    }

    public ClassLoader buildClassLoader(ClassLoader parent){
        return new URLClassLoader(toUrls(), parent);
    }

    private Stream<Classpath> stream(){
        return list.stream();
    }

    private URL[] toUrls(){
        return stream().map(Classpath::toUrl)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toArray(URL[]::new);
    }
}
