package jp.cafebabe.pochi.nasubi;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class Either<L, R> {
    private Optional<L> left;
    private Optional<R> right;

    private Either(Optional<L> left, Optional<R> right) {
        if(left.isPresent() && right.isPresent())
            throw new IllegalArgumentException("both parameters are not null, either should be null");
        if(!left.isPresent() && !right.isPresent())
            throw new IllegalArgumentException("both parameters are null, either should be non-null");
        this.left = left;
        this.right = right;
    }

    public Either<R, L> swap() {
        return new Either<R, L>(right, left);
    }

    public Stream<L> leftStream() {
        return left.map(value -> Stream.of(value))
                .orElse(Stream.empty());
    }

    public Stream<R> rightStream() {
        return right.map(value -> Stream.of(value))
                .orElse(Stream.empty());
    }

    /**
     * Either left or right is <code>null</code>.
     * Therefore, given action should accept <code>null</code> value.
     */
    public void apply(BiConsumer<L, R> action) {
        action.accept(left.get(), right.get());
    }

    public void apply(Consumer<L> leftAction, Consumer<R> rightAction) {
        left.ifPresent(leftAction);
        right.ifPresent(rightAction);
    }

    public Optional<L> left(){
        return left;
    }

    public Optional<R> right() {
        return right;
    }

    public <LL, RR> Either<LL, RR> map(Function<L, LL> leftMapper, Function<R, RR> rightMapper) {
        return new Either<LL, RR>(left.map(leftMapper), right.map(rightMapper));
    }

    /**
     * Either left or right is <code>null</code>.
     * Therefore, given mapper should accept <code>null</code> values.
     */
    public <V> V map(BiFunction<L, R, V> mapper) {
        return mapper.apply(left.get(), right.get());
    }

    public boolean isLeft() {
        return left.isPresent();
    }

    public boolean isRight() {
        return right.isPresent();
    }

    /**
     * given <code>left</code> should not be <code>null</code>.
     */
    public static <L, R> Either<L, R> left(L left) {
        return new Either<L, R>(Optional.of(left), Optional.empty());
    }

    /**
     * given <code>right</code> should not be <code>null</code>.
     */
    public static <L, R> Either<L, R> right(R right) {
        return new Either<L, R>(Optional.empty(), Optional.of(right));
    }
}
