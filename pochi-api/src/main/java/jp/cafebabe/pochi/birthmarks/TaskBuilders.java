package jp.cafebabe.pochi.birthmarks;

import java.util.stream.Stream;

public interface TaskBuilders<T, V extends TaskBuilder<T>> {
    V builder(T type);

    boolean available(T type);

    Stream<T> availableTypes();
}
