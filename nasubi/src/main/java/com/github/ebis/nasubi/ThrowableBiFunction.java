package com.github.ebis.nasubi;

public interface ThrowableBiFunction<A1, A2, R, E extends Exception> {
    R apply(A1 argument1, A2 argument) throws E;
}
