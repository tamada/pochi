package jp.cafebabe.pochi.io;

@FunctionalInterface
public interface Dumper<T>{
    public abstract void print(T results);
}
