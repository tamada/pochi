package com.github.pochi.runner.birthmarks.entities;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.github.pochi.kunai.entries.ClassName;
import com.github.pochi.runner.util.TimeredList;
import com.github.pochi.runner.util.Unit;

public class Birthmarks{
    private TimeredList<Birthmark> birthmarks;

    public Birthmarks(TimeredList<Birthmark> list){
        this.birthmarks = list;
    }

    public Birthmarks(Stream<Birthmark> stream){
        this.birthmarks = new TimeredList<>(stream);
    }

    public Birthmarks filter(Predicate<Birthmark> predicate){
        return new Birthmarks(birthmarks.stream()
                .filter(predicate));
    }

    public Optional<Birthmark> find(ClassName name){
        return birthmarks.stream()
                .filter(birthmark -> birthmark.isSame(name))
                .reduce((first, second) -> first);
    }

    public long time(){
        return birthmarks.time();
    }

    public double time(Unit unit){
        return birthmarks.time(unit);
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

    public static Birthmarks merge(Birthmarks b1, Birthmarks b2){
        TimeredList<Birthmark> list = new TimeredList<>(b1.birthmarks);
        return new Birthmarks(list.merge(b2.birthmarks));
    }

    public static Birthmarks merge(Birthmarks b1, Birthmark birthmark){
        TimeredList<Birthmark> list = new TimeredList<>(Stream.of(birthmark));
        return new Birthmarks(list.mergeBefore(b1.birthmarks));
    }
}
