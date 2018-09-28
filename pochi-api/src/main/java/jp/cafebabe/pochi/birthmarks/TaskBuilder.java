package jp.cafebabe.pochi.birthmarks;

import jp.cafebabe.pochi.birthmarks.config.Configuration;

public interface TaskBuilder<T> {
    T type();

    Task<T> build(Configuration config);
}
