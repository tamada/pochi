package com.github.pochi.birthmarks.comparators;

import com.github.pochi.birthmarks.Task;
import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.entities.Birthmarks;
import com.github.pochi.birthmarks.pairs.Pair;
import com.github.pochi.birthmarks.pairs.PairMatcher;

public interface Comparator extends Task<ComparatorType> {
    @Override
    ComparatorType type();

    Comparisons compare(Birthmarks results, PairMatcher<Birthmark> maker);

    default Comparison compare(Pair<Birthmark> pair) {
        Similarity similarity = similarity(pair);
        return new Comparison(pair, similarity);
    }

    default Comparison compare(Birthmark left, Birthmark right) {
        return compare(new Pair<>(left, right));
    }

    Similarity similarity(Pair<Birthmark> pair);
}
