package com.github.pochi.birthmarks.entities;

public class Results<T extends Acceptor<T>> {
    private T result;

    public Results(T result){
        this.result = result;
    }

    public T result(){
        return result;
    }

    public void accept(Visitor<T> visitor){
        result.accept(visitor);
    }
}
