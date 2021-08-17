package jp.cafebabe.birthmarks.pairs;

import java.util.Collection;
import java.util.stream.Stream;

public interface Streamable<T> {
    Stream<T> stream();

    static <T> Streamable<T> wrap(Collection<T> collection) {
        return () -> collection.stream();
    }

    default long count() {
        return stream().count();
    }
}
