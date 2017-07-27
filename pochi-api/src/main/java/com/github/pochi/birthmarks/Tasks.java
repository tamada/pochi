package com.github.pochi.birthmarks;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tasks<T> {
    private List<Task<T>> list;

    public Tasks(Stream<Task<T>> stream){
        list = stream.collect(Collectors.toList());
    }

    public Stream<Task<T>> stream(){
        return list.stream();
    }
}
