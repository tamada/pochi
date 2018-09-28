package jp.cafebabe.pochi.birthmarks;

import java.util.Optional;
import java.util.stream.Stream;

import jp.cafebabe.pochi.birthmarks.config.Configuration;

public interface Task<S> {
    S type();

    Configuration configuration();

    default <T> Stream<T> filter(Stream<Optional<T>> stream){
        return stream.filter(Optional::isPresent)
                .map(Optional::get);
    }
}
