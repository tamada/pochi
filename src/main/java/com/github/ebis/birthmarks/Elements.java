package com.github.ebis.birthmarks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Elements<T> implements Iterable<Element<T>>, Serializable {
    private static final long serialVersionUID = -3045321683097990685L;

    List<Element<T>> elements = new ArrayList<>();

    public Elements(){
    }

    public Elements(List<Element<T>> list){
        elements.addAll(list);
    }

    public Elements(Stream<Element<T>> array){
        array.forEach(item -> elements.add(item));
    }

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

    public Elements<T> union(Elements<T> other){
        List<Element<T>> list = new ArrayList<>();

        list.addAll(elements);
        other.stream()
        .filter(item -> !elements.contains(item))
        .forEach(item -> list.add(item));

        return new Elements<>(list);
    }

    public Elements<T> intersect(Elements<T> other){
        Stream<Element<T>> stream = other.stream()
                .filter(item -> elements.contains(item));
        return new Elements<>(stream);
    }

    public static final <T> Elements<T> build(Stream<T> stream){
        return new Elements<>(stream.map(item -> new Element<>(item)));
    }
}