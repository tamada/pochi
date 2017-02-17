package com.github.ebis.birthmarks;

import com.github.ebis.birthmarks.comparators.ComparatorType;
import com.github.ebis.birthmarks.comparators.Comparisons;
import com.github.ebis.birthmarks.comparators.Similarity;
import com.github.ebis.birthmarks.entities.Birthmark;
import com.github.ebis.birthmarks.entities.Birthmarks;
import com.github.ebis.birthmarks.entities.Pair;
import com.github.ebis.birthmarks.entities.Results;
import com.github.ebis.config.Configuration;

public interface BirthmarkComparator extends Service<ComparatorType>{
    ComparatorType type();

    Results<Comparisons> compare(Results<Birthmarks> results, PairMaker maker, Configuration config);

    default Similarity compare(Pair<Birthmark> pair, Configuration config){
        return compare(pair.left(),
                pair.right(), config);
    }

    Similarity compare(Birthmark left, Birthmark right, Configuration config);
}
