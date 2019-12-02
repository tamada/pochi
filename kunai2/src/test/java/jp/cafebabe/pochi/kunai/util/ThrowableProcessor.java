package jp.cafebabe.pochi.kunai.util;

public interface ThrowableProcessor<E extends Exception> {
    void perform() throws E;
}
