package com.github.pochi.runner.birthmarks.entities;

import java.io.Serializable;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.github.pochi.kunai.entries.ClassName;

public class Birthmark implements Serializable{
    private static final long serialVersionUID = -2383836180204233756L;

    private Metadata metadata;
    private Elements elements;

    public Birthmark(Metadata metadata, Elements elements){
        this.metadata = metadata;
        this.elements = elements;
    }

    public boolean isSameClassName(Birthmark other){
        return metadata.hasSameName(other.metadata());
    }

    public ClassName className(){
        return metadata.className();
    }

    public void forEach(Consumer<Element> consumer){
        elements.forEach(consumer);
    }

    public boolean contains(Element element){
        return elements.contains(element);
    }

    public int elementCount(){
        return elements.size();
    }

    public void forEach(Predicate<Element> predicate, Consumer<Element> consumer){
        elements.forEach(predicate, consumer);
    }

    public boolean is(ClassName name){
        return metadata.hasSameName(name);
    }

    public Metadata metadata(){
        return metadata;
    }
}
