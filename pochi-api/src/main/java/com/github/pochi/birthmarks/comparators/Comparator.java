package com.github.pochi.birthmarks.comparators;

import java.util.Optional;
import java.util.function.BiConsumer;

import com.github.pochi.birthmarks.Task;
import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.entities.Birthmarks;
import com.github.pochi.birthmarks.pairs.Pair;
import com.github.pochi.birthmarks.pairs.PairMatcher;

public interface Comparator extends Task<ComparatorType> {
    @Override
    ComparatorType type();

    Comparisons compare(Birthmarks results, PairMatcher<Birthmark> maker, BiConsumer<Pair<Birthmark>, Exception> callback);

    Comparisons compare(Birthmarks left, Birthmarks right, PairMatcher<Birthmark> maker, BiConsumer<Pair<Birthmark>, Exception> callback);

    Optional<Similarity> similarity(Pair<Birthmark> pair, BiConsumer<Pair<Birthmark>, Exception> callback);

    default Comparisons compare(Birthmarks results, PairMatcher<Birthmark> maker) {
        return compare(results, maker, (pair, exception) -> {});
    }

    default Comparisons compare(Birthmarks left, Birthmarks right, PairMatcher<Birthmark> maker) {
        return compare(left, right, maker, (pair, exception) -> {});
    }

    default Optional<Comparison> compare(Pair<Birthmark> pair, BiConsumer<Pair<Birthmark>, Exception> callback) {
        Optional<Similarity> similarity = similarity(pair, callback);
        return similarity.map(sim -> new Comparison(pair, sim));
    }

    default Optional<Comparison> compare(Birthmark left, Birthmark right) {
        return compare(left, right, (pair, exception) -> {});
    }

    default Optional<Comparison> compare(Birthmark left, Birthmark right, BiConsumer<Pair<Birthmark>, Exception> callback) {
        return compare(new Pair<>(left, right), callback);
    }
}
