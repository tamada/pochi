package com.github.pochi.runner.birthmarks.entities;

public class Pair<T> {
    private T item1;
    private T item2;

    public Pair(T item1, T item2){
        this.item1 = item1;
        this.item2 = item2;
    }

    public T left(){
        return item1;
    }

    public T right(){
        return item2;
    }
}
