package jp.cafebabe.pochi.nasubi;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class Couplet<L, R> {
    private L left;
    private R right;

    public Couplet(L left, R right) {
        this.left = Objects.requireNonNull(left);
        this.right = Objects.requireNonNull(right);
    }

    public void apply(BiConsumer<L, R> action) {
        action.accept(left, right);
    }

    public boolean filter(BiPredicate<L, R> predicate) {
        return predicate.test(left, right);
    }

    public <F> F map(BiFunction<L, R, F> mapper) {
        return mapper.apply(left, right);
    }

    public <R1, R2> Couplet<R1, R2> map(Function<L, R1> leftMapper, Function<R, R2> rightMapper) {
        return new Couplet<>(leftMapper.apply(left), rightMapper.apply(right));
    }

    @SuppressWarnings("rawtypes")
    public boolean equals(Object other) {
        return getClass().isAssignableFrom(other.getClass()) && Objects.equals(left, ((Couplet) other).left)
                && Objects.equals(right, ((Couplet) other).right);
    }

    public int hashCode() {
        return Objects.hash(getClass(), left, right);
    }
}
