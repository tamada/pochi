package com.github.pochi.birthmarks.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Elements implements Serializable{
    private static final long serialVersionUID = -8713896078315146158L;

    private List<Element> list = new ArrayList<>();

    public Elements(Stream<Element> stream){
        stream.forEach(list::add);
    }

    public boolean contains(Element element){
        return list.contains(element);
    }

    public void forEach(Consumer<Element> consumer){
        list.stream()
        .forEach(consumer);
    }

    public int size(){
        return list.size();
    }

    public Elements filter(Predicate<Element> predicate){
        return new Elements(list.stream()
                .filter(predicate));
    }

    public static Elements empty(){
        return new Elements(Stream.of());
    }

    public Elements merge(Elements other){
        return new Elements(Stream.concat(list.stream(), 
                other.list.stream()));
    }
}
