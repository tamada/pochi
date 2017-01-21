package com.github.kunai.util;

public interface ThrowableBiConsumer<S1, S2, E extends Exception> {
    void apply(S1 s1, S2 s2) throws E;
}
