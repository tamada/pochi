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
