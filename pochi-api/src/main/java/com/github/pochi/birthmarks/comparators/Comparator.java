package com.github.pochi.birthmarks.comparators;

import java.util.function.BiConsumer;

import com.github.pochi.birthmarks.Task;
import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.entities.Birthmarks;
import com.github.pochi.birthmarks.pairs.Pair;
import com.github.pochi.birthmarks.pairs.PairMatcher;

public interface Comparator extends Task<ComparatorType> {
    @Override
    ComparatorType type();

    Comparisons compare(Birthmarks results, PairMatcher maker, BiConsumer<Birthmark, Birthmark> action);

    default Comparisons compare(Birthmarks results, PairMatcher maker) {
        return compare(results, maker, (left, right) -> {});
    }

    default Comparison compare(Pair<Birthmark> pair, BiConsumer<Birthmark, Birthmark> action) {
        action.accept(pair.left(), pair.right());
        Similarity similarity = similarity(pair);
        return new Comparison(pair, similarity);
    }

    default Comparison compare(Birthmark left, Birthmark right) {
        return compare(left, right, (birthmark1, birthmark2) -> {});
    }

    default Comparison compare(Birthmark left, Birthmark right, BiConsumer<Birthmark, Birthmark> action) {
        return compare(new Pair<>(left, right), action);
    }

    Similarity similarity(Pair<Birthmark> pair);
}
