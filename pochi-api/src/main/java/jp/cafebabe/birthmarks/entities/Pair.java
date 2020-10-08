package jp.cafebabe.birthmarks.entities;

import java.util.Objects;
import java.util.function.Supplier;

public class Pair<T> extends Couple<T, T>{
    public Pair(T item1, T item2){
        super(item1, item2);
    }

    public <R> R ifEquals(R trueCaseValue, R falseCaseValue) {
        return ifEquals(() -> trueCaseValue, () -> falseCaseValue);
    }

    public <R> R ifEquals(Supplier<R> trueCase, Supplier<R> falseCase) {
        if(Objects.equals(left(), right()))
            return trueCase.get();
        return falseCase.get();
    }

    @Override
    public String toString() {
        return this.map(
                (left, right) -> String.format("<%s, %s>", left, right));
    }
}
