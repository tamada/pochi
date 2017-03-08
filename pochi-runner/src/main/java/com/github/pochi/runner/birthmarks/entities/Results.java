package com.github.pochi.runner.birthmarks.entities;

public class Results<T> {
    private T result;

    public Results(T result){
        this.result = result;
    }

    public T result(){
        return result;
    }
}
