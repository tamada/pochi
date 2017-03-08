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
    private BirthmarkType type;

    public Metadata(ClassName name, URI location, BirthmarkType type){
        this.name = name;
        this.location = location;
        this.type = type;
    }

    public ClassName className(){
        return name;
    }

    public boolean isSame(BirthmarkType otherType){
        return Objects.equals(type(), otherType);
    }

    public boolean isSame(ClassName otherName){
        return Objects.equals(className(), otherName);
    }

    public boolean isSame(URI otherLocation){
        return Objects.equals(location(), otherLocation);
    }

    public URI location(){
        return location;
    }

    @Override
    public String toString(){
        return new StringJoiner(",").add(name.toString())
                .add(location.toASCIIString())
                .add(type.toString())
                .toString();
    }

    public BirthmarkType type(){
        return type;
    }

    public static Metadata build(Entry entry, BirthmarkType type){
        return new Metadata(entry.className(), entry.loadFrom(), type);
    }

    public static Metadata build(Entry entry){
        return build(entry, BirthmarkType.UNKNOWN);
    }
}
