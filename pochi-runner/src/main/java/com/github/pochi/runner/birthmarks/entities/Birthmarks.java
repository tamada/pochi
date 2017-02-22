package com.github.pochi.runner.birthmarks.entities;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.pochi.kunai.entries.ClassName;
import com.github.pochi.runner.util.Timer;

public class Birthmarks extends Timer{
    private List<Birthmark> birthmarks;

    public Birthmarks(Stream<Birthmark> stream){
        timer(() -> birthmarks = stream
                .collect(Collectors.toList()));
    }

    public Optional<Birthmark> find(ClassName name){
        return birthmarks.stream()
                .filter(birthmark -> birthmark.is(name))
                .reduce((first, second) -> first);
    }

    public Stream<Birthmark> stream(){
        return birthmarks.stream();
    }

    public int count(){
        return birthmarks.size();
    }

    public void forEach(Consumer<Birthmark> consumer){
        stream().forEach(consumer);
    }

    public void forEach(Predicate<Birthmark> predicate, Consumer<Birthmark> consumer){
        birthmarks.stream()
        .filter(predicate).forEach(consumer);
    }

    public Birthmarks append(Birthmarks birthmarks){
        return append(birthmarks.stream());
    }

    public Birthmarks append(Stream<Birthmark> stream){
        return new Birthmarks(Stream.concat(
                birthmarks.stream(), stream));
    }
}
