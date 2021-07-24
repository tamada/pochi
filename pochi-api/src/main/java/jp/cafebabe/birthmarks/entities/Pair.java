package jp.cafebabe.birthmarks.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Supplier;

public class Pair<T extends Serializable> extends Couple<T, T> implements Serializable {
    public Pair(T item1, T item2){
        super(item1, item2);
    }

    @Override
    public String toString() {
        return this.map(
                (left, right) -> String.format("<%s, %s>", left, right));
    }
}
