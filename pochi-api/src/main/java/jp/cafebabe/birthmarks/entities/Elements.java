package jp.cafebabe.birthmarks.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Elements<T> implements Acceptor<T>, Serializable, Iterable<T> {
    private static final long serialVersionUID = -8713896078315146158L;

    private List<T> list = new ArrayList<>();

    public Elements(Stream<T> stream){
        stream.forEach(list::add);
    }

    public boolean contains(T element){
        return list.contains(element);
    }

    public Iterator<T> iterator() {
        return list.iterator();
    }

    public int size(){
        return list.size();
    }

    public Elements<T> filter(Predicate<T> predicate){
        return new Elements<>(list.stream()
                .filter(predicate));
    }

    public static <T> Elements<T> empty(){
        return new Elements<>(Stream.of());
    }

    public Elements<T> merge(Elements<T> other){
        return new Elements<>(Stream.concat(list.stream(),
                other.list.stream()));
    }

    @Override
    public void accept(Visitor<T> visitor) {
        list.stream().forEach(element -> visitor.visitElement(element));
    }

    @Override
    public String toString() {
        return list.stream()
                .map(element -> element.toString())
                .collect(Collectors.joining(","));
    }
}
