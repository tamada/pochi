package com.github.kunai.util;

@FunctionalInterface
public interface ThrowableConsumer<S, E extends Exception> {
    void apply(S argument) throws E;
}
