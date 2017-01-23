package com.github.kunai.util;

public interface ThrowableProcessor<E extends Exception> {
    void perform() throws E;
}
