package jp.cafebabe.pochi.util;

import java.util.function.BiPredicate;
import java.util.function.Supplier;

public class Tuple<T> {
    private T left;
    private T right;

    private Tuple(T left, T right) {
        this.left = left;
        this.right = right;
    }

    public static <T> Tuple<T> of(T left, T right) {
        return new Tuple<>(left, right);
    }

    public <R> R ifEquals(BiPredicate<T, T> predicate, R trueCaseValue, R falseCaseValue) {
        return ifEquals(predicate, () -> trueCaseValue, () -> falseCaseValue);
    }

    public <R> R ifEquals(BiPredicate<T, T> predicate, Supplier<R> trueCase, Supplier<R> falseCase) {
        if(predicate.test(left, right))
            return trueCase.get();
        return falseCase.get();
    }
}
