package com.github.ebis.birthmarks.comparators;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.ebis.util.Timer;

public class Comparisons extends Timer{
    private List<Comparison> comparisons;

    public Comparisons(Stream<Comparison> stream){
        timer(() -> comparisons = stream
                .collect(Collectors.toList()));
    }

    public void forEach(Consumer<Comparison> consumer){
        comparisons.forEach(consumer);
    }

    public void forEach(Predicate<Comparison> predicate, Consumer<Comparison> consumer){
        comparisons.stream()
        .filter(predicate)
        .forEach(consumer);
    }
}
