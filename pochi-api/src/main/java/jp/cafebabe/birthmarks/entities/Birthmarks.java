package jp.cafebabe.birthmarks.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.vavr.control.Either;
import jp.cafebabe.birthmarks.pairs.Streamable;
import jp.cafebabe.kunai.entries.ClassName;

public class Birthmarks<T> implements Acceptor<T>, Iterable<Birthmark<T>>, Streamable<Birthmark<T>>, Serializable {
    private List<Birthmark<T>> list;
    private List<Exception> exceptions;

    public Birthmarks(){
        this(Stream.empty());
    }

    public Birthmarks(Stream<Either<Exception, Birthmark<T>>> stream) {
        list = new ArrayList<>();
        exceptions = new ArrayList<>();
        stream.forEach(either -> either.bimap(t -> exceptions.add(t), r -> list.add(r)));
    }

    public Optional<Birthmark<T>> unify() {
        return BirthmarksMerger.unifyTo(this);
    }

    public Stream<Birthmark<T>> find(ClassName name) {
        return list.stream()
                .filter(birthmark -> birthmark.isSame(name));
    }

    public Stream<Birthmark<T>> stream() {
        return list.stream();
    }

    public Stream<Exception> exceptionStream() {
        return exceptions.stream();
    }

    public long count() {
        return list.size();
    }

    @Override
    public Iterator<Birthmark<T>> iterator() {
        return list.iterator();
    }

    public Birthmarks<T> merge(Stream<Birthmark<T>> stream) {
        return new Birthmarks<>(Stream.concat(stream(), stream)
                .map(item -> Either.right(item)));
    }

    public Birthmarks<T> merge(Birthmarks<T> other) {
        return merge(other.stream());
    }

    public static <T> Birthmarks<T> of(Stream<Birthmark<T>> stream) {
        return ofEither(stream.map(item -> Either.right(item)));
    }

    public static <T> Birthmarks<T> ofEither(Stream<Either<Exception, Birthmark<T>>> stream) {
        return new Birthmarks<>(stream);
    }

    @Override
    public void accept(Visitor<T> visitor) {
        list.stream()
                .forEach(birthmark -> birthmark.accept(visitor));
    }
}
