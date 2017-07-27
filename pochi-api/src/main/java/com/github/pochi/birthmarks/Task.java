package com.github.pochi.birthmarks;

import java.util.Optional;
import java.util.stream.Stream;

import com.github.pochi.birthmarks.config.Configuration;

public interface Task<S> {
    S type();

    Configuration configuration();

    default <T> Stream<T> filter(Stream<Optional<T>> stream){
        return stream.filter(Optional::isPresent)
                .map(Optional::get);
    }
}
