package jp.cafebabe.pochi.birthmarks.pairs;

import jp.cafebabe.pochi.nasubi.Couple;

public class Pair<T> extends Couple<T, T>{
    public Pair(T item1, T item2){
        super(item1, item2);
    }

    public T left() {
        return map((left, right) -> left);
    }

    public T right() {
        return map((left, right) -> right);
    }

    @Override
    public String toString() {
        return this.map(
                (left, right) -> String.format("<%s, %s>", left, right));
    }
}
