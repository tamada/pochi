package com.github.pochi.runner.birthmarks.io;

@FunctionalInterface
public interface Dumper<T>{
    public abstract void print(T results);
}
