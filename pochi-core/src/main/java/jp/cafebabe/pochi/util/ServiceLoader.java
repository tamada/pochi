package jp.cafebabe.pochi.util;

import io.vavr.control.Try;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class ServiceLoader<T> {
    private List<String> list;

    ServiceLoader(Stream<String> stream){
        list = stream.collect(toList());
    }

    public Stream<T> instantiate(Function<Class<T>, Optional<T>> instantiateFunction){
        return stream()
                .map(instantiateFunction::apply)
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    public Stream<T> instantiate(){
        return stream().map(clazz -> instantiate(clazz))
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    public Stream<Class<T>> stream(){
        return toClassStream()
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    private Stream<Optional<Class<T>>> toClassStream(){
        return list.stream()
                .map(this::toClass);
    }

    @SuppressWarnings("unchecked")
    private Optional<Class<T>> toClass(String className){
        return Try.of(() -> (Class<T>)Class.forName(className))
                .toJavaOptional();
    }

    private Optional<T> instantiate(Class<T> clazz) {
        return Try.of(() -> clazz.getDeclaredConstructor()
                .newInstance())
                .toJavaOptional();
    }
}
