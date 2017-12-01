package com.github.pochi.izumo;

import java.util.Objects;

public class Pair<T> {
    private T left;
    private T right;

    public Pair(T left, T right) {
        this.left = left;
        this.right = right;
    }

    public T left() {
        return left;
    }

    public T right() {
        return right;
    }

    public int hashCode() {
        return Objects.hash(getClass().getName(), left, right);
    }

    @SuppressWarnings("unchecked")
    public boolean equals(Object other) {
        return other instanceof Pair
                && Objects.equals(left, ((Pair<T>)other).left)
                && Objects.equals(right, ((Pair<T>)other).right);
    }
}
