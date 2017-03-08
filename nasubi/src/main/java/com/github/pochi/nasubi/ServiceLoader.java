package com.github.pochi.nasubi;

import static com.github.pochi.nasubi.Exceptions.map;
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

    public Stream<T> instantiate(Function<Class<T>, T> instantiateFunction){
        return stream()
                .map(instantiateFunction::apply);
    }

    public Stream<T> instantiate(){
        return stream().map(clazz -> map(clazz, c -> c.newInstance()))
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
        return Exceptions.map(className, 
                item -> (Class<T>)Class.forName(item));
    }
}
