package com.github.ebis.birthmarks.rules;

public class Snippet{
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
}
