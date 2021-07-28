package jp.cafebabe.birthmarks.entities;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jp.cafebabe.birthmarks.pairs.Streamable;
import jp.cafebabe.kunai.entries.ClassName;

public class Birthmarks<T> implements Acceptor<T>, Iterable<Birthmark<T>>, Streamable<Birthmark<T>>, Serializable {
    private List<Birthmark<T>> list;

    public Birthmarks(){
        this(Stream.empty());
    }

    public Birthmarks(Stream<Birthmark<T>> stream) {
        this.list = stream.collect(Collectors.toList());
    }

    public Stream<Birthmark<T>> find(ClassName name) {
        return list.stream()
                .filter(birthmark -> birthmark.isSame(name));
    }

    public Stream<Birthmark<T>> stream() {
        return list.stream();
    }

    public long count() {
        return list.size();
    }

    @Override
    public Iterator<Birthmark<T>> iterator() {
        return list.iterator();
    }

    public Birthmarks<T> merge(Stream<Birthmark<T>> stream) {
        return new Birthmarks<>(Stream.concat(stream(), stream));
    }

    public Birthmarks<T> merge(Birthmarks<T> other) {
        return merge(other.stream());
    }

    @Override
    public void accept(Visitor<T> visitor) {
        list.stream()
                .forEach(birthmark -> birthmark.accept(visitor));
    }
}
