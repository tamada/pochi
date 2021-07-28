package jp.cafebabe.birthmarks.entities;

import jp.cafebabe.birthmarks.entities.elements.FrequencyElements;
import jp.cafebabe.birthmarks.entities.elements.ListElements;

import java.io.Serializable;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface Elements<T> extends Iterable<T>, Acceptor<T>, Serializable {

    FrequencyElements asFrequencies();

    ListElements asList();

    ListElements asSet();

    boolean contains(String key);

    Elements<T> filter(Predicate<T> value);

    Iterator<T> iterator();

    Stream<T> stream();

    Elements<T> merge(Elements<T> other);

    int size();

    static ListElements listElements(String... elements) {
        return ListElements.of(elements);
    }

    static ListElements listElements(Stream<String> elements) {
        return ListElements.of(elements);
    }

    static ListElements setElements(String... elements) {
        return ListElements.asSetOf(elements);
    }

    static FrequencyElements frequencyElements(String... elements) {
        return FrequencyElements.of(elements);
    }

    static FrequencyElements frequencyElements(Stream<Frequency> stream) {
        return FrequencyElements.of(stream);
    }
}
