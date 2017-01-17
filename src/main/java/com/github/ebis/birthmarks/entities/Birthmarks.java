package com.github.ebis.birthmarks.entities;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.kunai.entries.ClassName;

public class Birthmarks {
    private List<Birthmark> birthmarks;

    public Birthmarks(Stream<Birthmark> stream){
        birthmarks = stream
                .collect(Collectors.toList());
    }

    public Optional<Birthmark> find(ClassName name){
        return birthmarks.stream()
                .filter(birthmark -> birthmark.is(name))
                .reduce((first, second) -> first);
    }

    public Stream<Birthmark> stream(){
        return birthmarks.stream();
    }

    public void forEach(Consumer<Birthmark> consumer){
        birthmarks.stream()
        .forEach(consumer);
    }

    public Birthmarks append(Birthmarks birthmarks){
        return append(birthmarks.stream());
    }

    public Birthmarks append(Stream<Birthmark> stream){
        return new Birthmarks(Stream.concat(
                birthmarks.stream(), stream));
    }
}
