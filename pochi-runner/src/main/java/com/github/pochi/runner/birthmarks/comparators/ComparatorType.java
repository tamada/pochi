package com.github.pochi.runner.birthmarks.comparators;

import java.io.Serializable;
import java.util.Objects;

public class ComparatorType implements Serializable{
    private static final long serialVersionUID = 5510440148918700265L;

    private String type;

    public ComparatorType(String type){
        this.type = type;
    }

    @Override
    public String toString(){
        return type;
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(type);
    }

    @Override
    public boolean equals(Object other){
        return other instanceof ComparatorType
                && Objects.equals(type, ((ComparatorType)other).type);
    }
}
