package com.github.pochi.birthmarks.entities;

public interface Acceptor<T> {
    void accept(Visitor visitor);

}
