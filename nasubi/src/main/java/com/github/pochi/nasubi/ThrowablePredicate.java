package com.github.pochi.nasubi;

@FunctionalInterface
public interface ThrowablePredicate<S, E extends Exception> {
    boolean test(S argument) throws E;
}
