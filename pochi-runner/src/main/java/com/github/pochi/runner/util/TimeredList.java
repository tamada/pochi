package com.github.pochi.runner.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TimeredList<T> extends Timer{
    private List<T> list;

    public TimeredList(Stream<T> stream){
        timer(() -> list = stream
                .collect(Collectors.toList()));
    }

    public TimeredList(TimeredList<T> self){
        this.time = self.time;
        this.list = new ArrayList<>(self.list);
    }

    private TimeredList(long time, Stream<T> stream){
        this.time = time;
        list = stream.collect(Collectors.toList());
    }

    public Stream<T> stream(){
        return list.stream();
    }

    public void forEach(Consumer<T> consumer){
        stream().forEach(consumer);
    }

    public int size(){
        return list.size();
    }

    public TimeredList<T> merge(TimeredList<T> otherList){
        this.time += otherList.time;
        return new TimeredList<>(time, Stream.concat(
                stream(), otherList.stream()));
    }

    public TimeredList<T> mergeBefore(TimeredList<T> otherList){
        this.time += otherList.time;
        return new TimeredList<>(time, Stream.concat(
                otherList.stream(), stream()));
    }
}
