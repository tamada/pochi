package jp.cafebabe.pochi.birthmarks.io;

@FunctionalInterface
public interface Dumper<T>{
    public abstract void print(T results);
}
