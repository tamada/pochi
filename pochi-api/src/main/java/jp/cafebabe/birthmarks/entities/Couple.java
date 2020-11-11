package jp.cafebabe.birthmarks.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class Couple<L extends Serializable, R extends Serializable> implements Serializable {
    private L left;
    private R right;

    public Couple(L left, R right) {
        this.left = Objects.requireNonNull(left);
        this.right = Objects.requireNonNull(right);
    }

    public L left() {
        return left;
    }

    public R right() {
        return right;
    }

    public static <L extends Serializable, R extends Serializable> Couple<L, R> of(L left, R right) {
        return new Couple<>(left, right);
    }

    public void apply(BiConsumer<L, R> action) {
        action.accept(left, right);
    }

    public boolean filter(BiPredicate<L, R> predicate) {
        return predicate.test(left, right);
    }

    public Couple<R, L> swap() {
        return Couple.of(right, left);
    }

    public <F> F map(BiFunction<L, R, F> mapper) {
        return mapper.apply(left, right);
    }

    public <L2 extends Serializable, R2 extends Serializable> Couple<L2, R2> map(Function<L, L2> leftMapper, Function<R, R2> rightMapper) {
        return new Couple<>(leftMapper.apply(left), rightMapper.apply(right));
    }

    public boolean equals(Object other) {
        return getClass().isAssignableFrom(other.getClass())
                && Objects.equals(left, ((Couple) other).left)
                && Objects.equals(right, ((Couple) other).right);
    }

    public int hashCode() {
        return Objects.hash(getClass(), left, right);
    }
}
