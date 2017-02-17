package com.github.ebis.nasubi;

public interface ThrowableFunction<A, R, E extends Exception> {
    R apply(A argument) throws E;
}
