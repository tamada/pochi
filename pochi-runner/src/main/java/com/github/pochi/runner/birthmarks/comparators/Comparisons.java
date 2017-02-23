package com.github.pochi.runner.birthmarks.comparators;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.github.pochi.runner.util.TimeredList;
import com.github.pochi.runner.util.Unit;

public class Comparisons{
    private TimeredList<Comparison> comparisons;

    public Comparisons(Stream<Comparison> stream){
        comparisons = new TimeredList<>(stream);
    }

    public Comparisons filter(Predicate<Comparison> predicate){
        return new Comparisons(comparisons.stream()
                .filter(predicate));
    }

    public void forEach(Consumer<Comparison> consumer){
        comparisons.forEach(consumer);
    }

    public void forEach(Predicate<Comparison> predicate, Consumer<Comparison> consumer){
        comparisons.stream()
        .filter(predicate)
        .forEach(consumer);
    }

    public long time(){
        return comparisons.time();
    }

    public double time(Unit unit){
        return comparisons.time(unit);
    }
}
