package com.github.pochi.birthmarks.entities;

public interface Visitor<T> {
    void visit(T target);
}
