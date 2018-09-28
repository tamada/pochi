package jp.cafebabe.pochi.izumo;

@FunctionalInterface
public interface CorrespondenceChecker<T> {
    boolean isCorrespond(T item1, T item2);
}
