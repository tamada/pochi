package com.github.pochi.birthmarks.entities;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.pochi.kunai.entries.ClassName;

public class Birthmarks implements Acceptor<Birthmarks>, Iterable<Birthmark> {
    private List<Birthmark> list;

    public Birthmarks(Stream<Birthmark> stream) {
        this.list = stream.collect(Collectors.toList());
    }

    public Stream<Birthmark> find(ClassName name) {
        return list.stream().filter(birthmark -> birthmark.isSame(name));
    }

    public Stream<Birthmark> stream() {
        return list.stream();
    }

    public int count() {
        return list.size();
    }

    @Override
    public Iterator<Birthmark> iterator() {
        return list.iterator();
    }

    public Birthmarks merge(Stream<Birthmark> stream) {
        return new Birthmarks(Stream.concat(stream(), stream));
    }

    public Birthmarks merge(Birthmarks other) {
        return merge(other.stream());
    }

    @Override
    public void accept(Visitor visitor) {
        list.stream().forEach(birthmark -> birthmark.accept(visitor));
    }
}
