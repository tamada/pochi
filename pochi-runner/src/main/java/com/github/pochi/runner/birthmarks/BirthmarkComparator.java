package com.github.pochi.runner.birthmarks;

import com.github.pochi.runner.birthmarks.comparators.ComparatorType;
import com.github.pochi.runner.birthmarks.comparators.Comparison;
import com.github.pochi.runner.birthmarks.comparators.Comparisons;
import com.github.pochi.runner.birthmarks.comparators.Similarity;
import com.github.pochi.runner.birthmarks.entities.Birthmark;
import com.github.pochi.runner.birthmarks.entities.Birthmarks;
import com.github.pochi.runner.birthmarks.entities.Pair;
import com.github.pochi.runner.config.Configuration;

public interface BirthmarkComparator extends Service<ComparatorType> {
    @Override
    ComparatorType type();

    Comparisons compare(Birthmarks results, PairMaker maker, Configuration config);

    default Comparison compare(Pair<Birthmark> pair, Configuration config) {
        Similarity similarity = similarity(pair.left(), pair.right(), config);
        return new Comparison(pair, similarity);
    }

    default Comparison compare(Birthmark left, Birthmark right, Configuration config) {
        return compare(new Pair<>(left, right), config);
    }

    Similarity similarity(Birthmark left, Birthmark right, Configuration config);
}
