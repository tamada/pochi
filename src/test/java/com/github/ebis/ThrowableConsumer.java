package com.github.ebis;

@FunctionalInterface
public interface ThrowableConsumer<S extends Exception>{
    void consume() throws S;
}
