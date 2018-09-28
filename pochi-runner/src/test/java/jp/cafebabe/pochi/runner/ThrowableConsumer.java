package jp.cafebabe.pochi.runner;

@FunctionalInterface
public interface ThrowableConsumer<S extends Exception>{
    void consume() throws S;
}
