package jp.cafebabe.birthmarks;

import java.util.Optional;
import java.util.stream.Stream;

import io.vavr.control.Either;
import jp.cafebabe.birthmarks.config.Configuration;

public interface Task<S> {
    S type();

    Configuration configuration();

    default <T> Stream<T> stripOptional(Stream<Optional<T>> stream){
        return stream.filter(Optional::isPresent)
                .map(Optional::get);
    }

    default <E, T> Stream<T> stripEither(Stream<Either<E, T>> stream) {
        return stream.filter(either -> either.isRight())
                .map(either -> either.get());
    }
}
