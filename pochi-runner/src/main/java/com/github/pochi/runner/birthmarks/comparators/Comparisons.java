package com.github.pochi.runner.birthmarks.comparators;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.github.pochi.runner.util.TimeredList;
import com.github.pochi.runner.util.Unit;

public class Comparisons{
    private TimeredList<Comparison> list;

    public Comparisons(Stream<Comparison> stream){
        list = new TimeredList<>(stream);
    }

    public Comparisons filter(Predicate<Comparison> predicate){
        return new Comparisons(list.stream()
                .filter(predicate));
    }

    public void forEach(Consumer<Comparison> consumer){
        list.forEach(consumer);
    }

    public long time(){
        return list.time();
    }

    public double time(Unit unit){
        return list.time(unit);
    }
}
