package com.github.pochi.birthmarks;

import java.io.Serializable;
import java.util.Objects;

public class ParserType implements Serializable {
    private static final long serialVersionUID = 4448374227045987362L;

    private String type;

    public ParserType(String type){
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
        return other instanceof ParserType
                && Objects.equals(type, ((ParserType)other).type);
    }
}
