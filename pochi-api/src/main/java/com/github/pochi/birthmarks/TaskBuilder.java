package com.github.pochi.birthmarks;

import com.github.pochi.birthmarks.config.Configuration;

public interface TaskBuilder<T> {
    T type();

    Task<T> build(Configuration config);
}
