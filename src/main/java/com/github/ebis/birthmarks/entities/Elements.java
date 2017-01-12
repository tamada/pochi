package com.github.ebis.birthmarks.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Elements {
    private List<Element> elements = new ArrayList<>();

    public Elements(Stream<Element> stream){
        stream.forEach(
                item -> elements.add(item));
    }

    public void forEach(Consumer<Element> consumer){
        elements.stream()
        .forEach(consumer);
    }

    public void forEach(Predicate<Element> predicate, Consumer<Element> consumer){
        elements.stream()
        .filter(predicate)
        .forEach(consumer);
    }
}
