package com.github.ebis.birthmarks.computers;

import com.github.ebis.birthmarks.Birthmark;
import com.github.ebis.birthmarks.Elements;

public class JaccardIndexComputer implements Computer {
    @Override
    public <T> Similarity compare(Birthmark<T> b1, Birthmark<T> b2) {
        Elements<T> e1 = b1.elements();
        Elements<T> e2 = b2.elements();

        Elements<T> intersection = e1.intersect(e2);
        Elements<T> union = e1.union(e2);

        return new Similarity(1.0 * intersection.size() / union.size());
    }
}
