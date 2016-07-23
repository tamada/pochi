package com.github.ebis.birthmarks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Elements<T> implements Iterable<Element<T>>, Serializable {
    private static final long serialVersionUID = -3045321683097990685L;

    List<Element<T>> elements = new ArrayList<>();

    public final Element<T> get(int index) {
        return elements.get(index);
    }

    @Override
    public Iterator<Element<T>> iterator() {
        return elements.iterator();
    }

    public final int size() {
        return elements.size();
    }

    public Stream<Element<T>> stream() {
        return elements.stream();
    }

    public Set<T> toSet() {
        return elements.stream().map(e -> e.getValue()).distinct().collect(Collectors.toSet());
    }
}
