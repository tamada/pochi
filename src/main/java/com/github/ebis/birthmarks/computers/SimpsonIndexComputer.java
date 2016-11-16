package com.github.ebis.birthmarks.computers;

import com.github.ebis.birthmarks.Birthmark;
import com.github.ebis.birthmarks.Elements;

public class SimpsonIndexComputer implements Computer {
    @Override
    public <T> Similarity compare(Birthmark<T> b1, Birthmark<T> b2) {
        Elements<T> e1 = b1.elements();
        Elements<T> e2 = b1.elements();
        Elements<T> union = e1.intersect(e2);

        return new Similarity(1.0 * union.size() / Math.min(e1.size(), e2.size()));
    }
}
