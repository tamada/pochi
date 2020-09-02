package jp.cafebabe.birthmarks;

import jp.cafebabe.birthmarks.config.Configuration;

public interface TaskBuilder<T> {
    T type();

    Task<T> build(Configuration config);
}
