package jp.cafebabe.kunai.util;

public interface ThrowableProcessor<E extends Exception> {
    void perform() throws E;
}
