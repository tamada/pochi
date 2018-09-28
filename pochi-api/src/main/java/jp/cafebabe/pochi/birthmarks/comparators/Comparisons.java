package jp.cafebabe.pochi.birthmarks.comparators;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Comparisons{
    private List<Comparison> list;

    public Comparisons(Stream<Comparison> stream){
        list = stream.collect(
                Collectors.toList());
    }

    public Comparisons filter(Predicate<Comparison> predicate){
        return new Comparisons(list.stream()
                .filter(predicate));
    }

    public void forEach(Consumer<Comparison> consumer){
        list.forEach(consumer);
    }
}
