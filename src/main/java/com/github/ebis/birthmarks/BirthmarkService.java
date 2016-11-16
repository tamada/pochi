package com.github.ebis.birthmarks;

import com.github.ebis.birthmarks.computers.Computer;
import com.github.ebis.birthmarks.extractor.Extractor;

public class BirthmarkService<T> {
    private Computer comparator;
    private Extractor<T> extractor;

    public BirthmarkService(Extractor<T> extractor, Computer comparator) {
        this.extractor = extractor;
        this.comparator = comparator;
    }

    public Computer computer() {
        return comparator;
    }

    public Extractor<T> extractor() {
        return extractor;
    }

    public BirthmarkType type() {
        return extractor.getName();
    }
}
