package com.github.pochi.runner.birthmarks.entities;

import java.io.Serializable;
import java.net.URI;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.github.pochi.kunai.entries.ClassName;

public class Birthmark implements Serializable{
    private static final long serialVersionUID = -2383836180204233756L;

    private Elements elements;
    private Metadata metadata;

    public Birthmark(Metadata metadata, Elements elements){
        this.metadata = metadata;
        this.elements = elements;
    }

    public ClassName className(){
        return metadata.className();
    }

    public boolean contains(Element element){
        return elements.contains(element);
    }

    public int elementCount(){
        return elements.size();
    }

    public void forEach(Consumer<Element> consumer){
        elements.forEach(consumer);
    }

    public Birthmark filter(Predicate<Element> predicate){
        return new Birthmark(metadata(),
                elements.filter(predicate));
    }

    public boolean isSame(ClassName name){
        return metadata.isSame(name);
    }

    public boolean isSame(URI location){
        return metadata.isSame(location);
    }

    public boolean isSame(BirthmarkType type){
        return metadata.isSame(type);
    }

    public URI location(){
        return metadata.location();
    }

    public Metadata metadata(){
        return metadata;
    }

    public BirthmarkType type(){
        return metadata.type();
    }
}
