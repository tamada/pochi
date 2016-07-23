package com.github.ebis.birthmarks.computers;

import com.github.ebis.birthmarks.Birthmark;

public interface Computer {
    <T> double compare(Birthmark<T> birthmark1, Birthmark<T> birthmark2);

    /**
     * returns the name of this comparator (almost comparison algorithm).
     * 
     * @return comparator name.
     */
    ComputerName getName();
}
