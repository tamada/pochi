package com.github.ebis.birthmarks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Birthmarks<T> implements Iterable<Birthmark<T>>, Serializable {
    private static final long serialVersionUID = -2157060520279910519L;

    private List<Birthmark<T>> birthmarks = new ArrayList<>();

    public Birthmarks(List<Birthmark<T>> birthmarks){
        this.birthmarks.addAll(birthmarks);
    }

    public Birthmarks(Stream<Birthmark<T>> stream){
        stream.forEach(item -> birthmarks.add(item));
    }

    @Override
    public Iterator<Birthmark<T>> iterator() {
        return birthmarks.iterator();
    }

    public Stream<Birthmark<T>> stream() {
        return birthmarks.stream();
    }
}
