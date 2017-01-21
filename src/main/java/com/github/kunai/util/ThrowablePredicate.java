package com.github.kunai.util;

@FunctionalInterface
public interface ThrowablePredicate<S, E extends Exception> {
    boolean test(S argument) throws E;
}
