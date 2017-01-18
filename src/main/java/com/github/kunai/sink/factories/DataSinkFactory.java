package com.github.kunai.sink.factories;

import java.nio.file.Path;
import java.util.function.Function;
import java.util.function.Predicate;

import com.github.kunai.sink.DataSink;

public class DataSinkFactory {
    private Predicate<Path> predicate;
    private Function<Path, DataSink> function;

    public DataSinkFactory(Predicate<Path> predicate, Function<Path, DataSink> function){
        this.predicate = predicate;
        this.function = function;
    }
    
    public boolean test(Path path){
        return predicate.test(path);
    }

    public DataSink create(Path path){
        return function.apply(path);
    }
}
