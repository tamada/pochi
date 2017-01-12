package com.github.ebis.birthmarks.entities;

import java.io.Serializable;
import java.net.URI;
import java.util.Objects;
import java.util.StringJoiner;

import com.github.kunai.entries.ClassName;
import com.github.kunai.entries.Entry;

public class Metadata implements Serializable{
    private static final long serialVersionUID = 5188434750286102391L;

    private URI location;
    private ClassName name;

    public Metadata(URI location, ClassName name){
        this.location = location;
        this.name = name;
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

    public static Metadata build(Entry entry){
        return new Metadata(entry.getPath().toUri(),
                entry.getClassName());
    }
}
