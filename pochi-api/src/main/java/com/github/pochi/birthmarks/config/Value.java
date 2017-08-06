package com.github.pochi.birthmarks.config;

import java.io.Serializable;
import java.util.Objects;

public abstract class Value implements Serializable{
    private static final long serialVersionUID = 8681917006679908602L;

    private String value;

    public Value(String value){
        this.value = value;
    }

    @Override
    public int hashCode(){
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object other){
        return Objects.equals(getClass(), other.getClass())
                && Objects.equals(value, ((Value)other).value);
    }

    @Override
    public String toString(){
        return String.valueOf(value);
    }
}
