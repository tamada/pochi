package com.github.kunai.util;

@FunctionalInterface
public interface ThrowableConsumer<S, E extends Exception> {
    void accept(S argument) throws E;
}
