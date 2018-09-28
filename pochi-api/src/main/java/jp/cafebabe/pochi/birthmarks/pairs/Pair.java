package jp.cafebabe.pochi.birthmarks.pairs;

import java.util.Objects;

public class Pair<T> {
    private T item1;
    private T item2;

    public Pair(T item1, T item2){
        this.item1 = item1;
        this.item2 = item2;
    }

    public T left(){
        return item1;
    }

    public T right(){
        return item2;
    }

    @Override
    public String toString() {
        return String.format("<%s, %s>", left(), right());
    }

    @Override
    public int hashCode() {
        return Objects.hash(left(), right(), getClass());
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object other) {
        return other instanceof Pair
                && Objects.equals(left(), ((Pair<T>)other).left())
                && Objects.equals(right(), ((Pair<T>)other).right());
    }
}
