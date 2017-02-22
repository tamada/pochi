package com.github.ebis.birthmarks;

import java.util.Optional;
import java.util.stream.Stream;

public interface Service<S> {
    S type();

    default <T> Stream<T> filter(Stream<Optional<T>> stream){
        return stream
                .filter(item -> item.isPresent())
                .map(item -> item.get());
    }
}
