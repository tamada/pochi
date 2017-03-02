package com.github.pochi.runner.birthmarks.entities;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.github.pochi.kunai.entries.ClassName;
import com.github.pochi.runner.util.TimeredList;
import com.github.pochi.runner.util.Unit;

public class Birthmarks{
    private TimeredList<Birthmark> list;

    public Birthmarks(TimeredList<Birthmark> list){
        this.list = list;
    }

    public Birthmarks(Stream<Birthmark> stream){
        this.list = new TimeredList<>(stream);
    }

    public Birthmarks filter(Predicate<Birthmark> predicate){
        return new Birthmarks(list.stream()
                .filter(predicate));
    }

    public Optional<Birthmark> find(ClassName name){
        return list.stream()
                .filter(birthmark -> birthmark.isSame(name))
                .reduce((first, second) -> first);
    }

    public long time(){
        return list.time();
    }

    public double time(Unit unit){
        return list.time(unit);
    }

    public Stream<Birthmark> stream(){
        return list.stream();
    }

    public int count(){
        return list.size();
    }

    public void forEach(Consumer<Birthmark> consumer){
        stream().forEach(consumer);
    }

    public void forEach(Predicate<Birthmark> predicate, Consumer<Birthmark> consumer){
        list.stream()
        .filter(predicate).forEach(consumer);
    }

    public Birthmarks merge(Birthmarks other){
        return Birthmarks.merge(this, other);
    }

    public static Birthmarks merge(Birthmarks b1, Birthmarks b2){
        TimeredList<Birthmark> list = new TimeredList<>(b1.list);
        return new Birthmarks(list.merge(b2.list));
    }

    public static Birthmarks merge(Birthmarks b1, Birthmark birthmark){
        TimeredList<Birthmark> list = new TimeredList<>(Stream.of(birthmark));
        return new Birthmarks(list.mergeBefore(b1.list));
    }
}
