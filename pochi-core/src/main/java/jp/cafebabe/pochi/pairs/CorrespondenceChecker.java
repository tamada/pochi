package jp.cafebabe.pochi.pairs;

import java.util.Objects;

@FunctionalInterface
public interface CorrespondenceChecker<T> {
    String extract(T item);

    default boolean isCorrespond(String item1, T item2) {
        return Objects.equals(item1, extract(item2));
    }

    default boolean isCorrespond(T item1, T item2) {
        return Objects.equals(extract(item1), extract(item2));
    }
}
