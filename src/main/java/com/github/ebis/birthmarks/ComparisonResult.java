package com.github.ebis.birthmarks;

import com.github.ebis.birthmarks.computers.Computer;
import com.github.ebis.birthmarks.computers.Similarity;

public class ComparisonResult<T> {
    private Similarity similarity;
    private Pair<T> pair;

    public ComparisonResult(Pair<T> pair, Computer computer){
        this(pair, pair.compute(computer));
    }

    public ComparisonResult(Pair<T> pair, Similarity similarity){
        this.pair = pair;
        this.similarity = similarity;
    }

    public boolean isSamePair(Pair<T> pair2){
        return pair.isSamePair(pair2);
    }
}
