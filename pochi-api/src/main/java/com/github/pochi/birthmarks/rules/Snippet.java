package com.github.pochi.birthmarks.rules;

import java.io.Serializable;
import java.util.Objects;

public class Snippet implements Serializable{
    private static final long serialVersionUID = 8096441643141119024L;

    private String value;

    public Snippet(String value){
        this.value = value;
    }

    String value(){
        return value;
    }

    @Override
    public String toString(){
        return value;
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(value);
    }

    @Override
    public boolean equals(Object other){
        return other instanceof Snippet
                && Objects.equals(value(), 
                        ((Snippet)other).value());
    }
}
