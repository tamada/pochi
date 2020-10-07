package jp.cafebabe.pochi.pairs;

@FunctionalInterface
public interface CorrespondenceChecker<T> {
    boolean isCorrespond(T item1, T item2);
}
