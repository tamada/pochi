package jp.cafebabe.nasubi;

@FunctionalInterface
public interface ThrowableConsumer<S, E extends Exception> {
    void accept(S argument) throws E;
}
