package com.github.ebis.birthmarks.computers;

import com.github.ebis.birthmarks.Birthmark;
import com.github.ebis.birthmarks.Pair;

public interface Computer {
    <T> Similarity compare(Birthmark<T> b1, Birthmark<T> b2);

    default <T> Similarity compare(Pair<T> pair){
        return pair.compute(this);
    }
}
