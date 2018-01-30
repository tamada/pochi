package com.github.pochi.birthmarks.config;

import java.io.Serializable;
import java.util.Objects;

public abstract class Value implements Serializable{
    private static final long serialVersionUID = 8681917006679908602L;

    private String string;

    public Value(String value){
        this.string = value;
    }

    @Override
    public int hashCode(){
        return Objects.hash(string, 
                getClass().getName());
    }

    @Override
    public boolean equals(Object other){
        return other != null
                && Objects.equals(getClass(), other.getClass())
                && Objects.equals(string, ((Value)other).string);
    }

    @Override
    public String toString(){
        return String.valueOf(string);
    }
}
