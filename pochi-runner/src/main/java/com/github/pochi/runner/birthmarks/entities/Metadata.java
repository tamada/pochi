package com.github.pochi.runner.birthmarks.entities;

import java.io.Serializable;
import java.net.URI;
import java.util.Objects;
import java.util.StringJoiner;

import com.github.pochi.kunai.entries.ClassName;
import com.github.pochi.kunai.entries.Entry;

public class Metadata implements Serializable{
    private static final long serialVersionUID = 5188434750286102391L;

    private URI location;
    private ClassName name;

    public Metadata(URI location, ClassName name){
        this.location = location;
        this.name = name;
    }

    public ClassName className(){
        return name;
    }

    @Override
    public String toString(){
        return new StringJoiner(",").add(name.toString())
                .add(location.toASCIIString())
                .toString();
    }

    public boolean hasSameName(ClassName otherName){
        return Objects.equals(name, otherName);
    }

    public boolean hasSameName(Metadata other){
        return hasSameName(other.className());
    }

    public static Metadata build(Entry entry){
        return new Metadata(entry.loadFrom(),
                entry.className());
    }
}
