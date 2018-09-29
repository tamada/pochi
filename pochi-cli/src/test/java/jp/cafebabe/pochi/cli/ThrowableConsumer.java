package jp.cafebabe.pochi.cli;

@FunctionalInterface
public interface ThrowableConsumer<S extends Exception>{
    void consume() throws S;
}
