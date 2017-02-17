package com.github.ebis.nasubi;

@FunctionalInterface
public interface ThrowablePredicate<S, E extends Exception> {
    boolean test(S argument) throws E;
}
