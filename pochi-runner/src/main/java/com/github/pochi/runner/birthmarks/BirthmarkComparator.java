package com.github.pochi.runner.birthmarks;

import com.github.pochi.runner.birthmarks.comparators.ComparatorType;
import com.github.pochi.runner.birthmarks.comparators.Comparisons;
import com.github.pochi.runner.birthmarks.comparators.Similarity;
import com.github.pochi.runner.birthmarks.entities.Birthmark;
import com.github.pochi.runner.birthmarks.entities.Birthmarks;
import com.github.pochi.runner.birthmarks.entities.Pair;
import com.github.pochi.runner.config.Configuration;

public interface BirthmarkComparator extends Service<ComparatorType>{
    ComparatorType type();

    Comparisons compare(Birthmarks results, PairMaker maker, Configuration config);

    default Similarity compare(Pair<Birthmark> pair, Configuration config){
        return compare(pair.left(),
                pair.right(), config);
    }

    Similarity compare(Birthmark left, Birthmark right, Configuration config);
}
