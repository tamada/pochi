package jp.cafebabe.pochi.birthmarks.pairs;

import java.util.Collection;
import java.util.stream.Stream;

public interface Streamable<T> {
    Stream<T> stream();

    public static <T> Streamable<T> wrap(Collection<T> collection) {
        return () -> collection.stream();
    }

    default long count() {
        return stream().count();
    }
}
