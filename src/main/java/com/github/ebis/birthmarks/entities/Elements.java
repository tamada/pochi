package com.github.ebis.birthmarks.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Elements implements Serializable{
    private static final long serialVersionUID = -8713896078315146158L;

    private List<Element> elements = new ArrayList<>();

    public Elements(Stream<Element> stream){
        stream.forEach(
                item -> elements.add(item));
    }

    public void forEach(Consumer<Element> consumer){
        forEach(item -> true, consumer);
    }

    public void forEach(Predicate<Element> predicate, Consumer<Element> consumer){
        elements.stream()
        .filter(predicate)
        .forEach(consumer);
    }
}
