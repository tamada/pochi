package jp.cafebabe.birthmarks.comparators;

import io.vavr.control.Either;
import jp.cafebabe.birthmarks.Task;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.entities.Pair;
import jp.cafebabe.birthmarks.pairs.PairMatcher;

public interface Comparator extends Task<ComparatorType> {
    @Override
    ComparatorType type();

    <T> Comparisons compare(Birthmarks<T> results, PairMatcher<Birthmark<T>> maker);

    <T> Comparisons compare(Birthmarks<T> left, Birthmarks<T> right, PairMatcher<Birthmark<T>> maker);

    <T> Either<Exception, Similarity> similarity(Pair<Birthmark<T>> pair);

    default <T> Either<Exception, Comparison<T>> compare(Pair<Birthmark<T>> pair){
        return similarity(pair)
                .map(sim -> new Comparison<>(pair, sim));
    }

    default <T> Either<Exception, Comparison<T>> compare(Birthmark<T> left, Birthmark<T> right) {
        return compare(new Pair<>(left, right));
    }
}
