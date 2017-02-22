package com.github.ebis.birthmarks.entities;

import java.util.Objects;

public class Results<T> {
    private BirthmarkType type;
    private T result;

    public Results(BirthmarkType type, T result){
        this.type = type;
        this.result = result;
    }

    public BirthmarkType type(){
        return type;
    }

    public boolean isSameType(BirthmarkType otherType){
        return Objects.equals(type, otherType);
    }

    public T result(){
        return result;
    }
}
