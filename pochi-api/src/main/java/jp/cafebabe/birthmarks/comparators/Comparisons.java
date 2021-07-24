package jp.cafebabe.birthmarks.comparators;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Comparisons<T> implements Serializable, Iterable<Comparison<T>> {
    private List<Comparison<T>> list;

    public Comparisons(Stream<Comparison<T>> stream){
        list = stream.collect(
                Collectors.toList());
    }

    public Comparisons<T> filter(Predicate<Comparison<T>> predicate){
        return new Comparisons<>(list.stream()
                .filter(predicate));
    }

    public Iterator<Comparison<T>> iterator() {
        return list.iterator();
    }
}
