package jp.cafebabe.pochi.birthmarks.comparators;

import java.util.Optional;
import java.util.function.BiConsumer;

import io.vavr.control.Either;
import jp.cafebabe.pochi.birthmarks.Task;
import jp.cafebabe.pochi.birthmarks.entities.Birthmark;
import jp.cafebabe.pochi.birthmarks.entities.Birthmarks;
import jp.cafebabe.pochi.birthmarks.pairs.Pair;
import jp.cafebabe.pochi.birthmarks.pairs.PairMatcher;

public interface Comparator extends Task<ComparatorType> {
    @Override
    ComparatorType type();

    Comparisons compare(Birthmarks results, PairMatcher<Birthmark> maker);

    Comparisons compare(Birthmarks left, Birthmarks right, PairMatcher<Birthmark> maker);

    Either<Exception, Similarity> similarity(Pair<Birthmark> pair);

    default Either<Exception, Comparison> compare(Pair<Birthmark> pair){
        return similarity(pair)
                .map(sim -> new Comparison(pair, sim));
    }

    default Either<Exception, Comparison> compare(Birthmark left, Birthmark right) {
        return compare(new Pair<>(left, right));
    }
}
