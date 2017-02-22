package com.github.pochi.runner.birthmarks.entities;

import java.io.Serializable;
import java.util.Objects;

public final class BirthmarkType implements Serializable{
    private static final long serialVersionUID = 6123912530056878788L;

    private String type;

    public BirthmarkType(String name){
        this.type = name;
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(type);
    }

    @Override
    public boolean equals(Object object){
        return object instanceof BirthmarkType
                && equals((BirthmarkType)object);
    }

    public boolean equals(BirthmarkType name){
        return Objects.equals(
                type, name.type);
    }

    @Override
    public String toString(){
        return type;
    }
}
