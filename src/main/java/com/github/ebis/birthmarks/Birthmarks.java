package com.github.ebis.birthmarks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Birthmarks implements Iterable<Birthmark<?>>, Serializable {
    private static final long serialVersionUID = -2157060520279910519L;

    private List<Birthmark<?>> birthmarks = new ArrayList<>();

    public void add(Birthmark<?> birthmark) {
        birthmarks.add(birthmark);
    }

    public <T> Birthmark<?> get(int index) {
        return birthmarks.get(index);
    }

    @Override
    public Iterator<Birthmark<?>> iterator() {
        return birthmarks.iterator();
    }

    public Stream<Birthmark<?>> stream() {
        return birthmarks.stream();
    }
}
