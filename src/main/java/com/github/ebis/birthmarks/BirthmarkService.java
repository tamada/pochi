package com.github.ebis.birthmarks;

import com.github.ebis.birthmarks.computers.Computer;
import com.github.ebis.birthmarks.extractor.Extractor;

public class BirthmarkService<T> {
    private Computer comparator;
    private Extractor<T> extractor;
    private String type;

    public BirthmarkService(String type, Extractor<T> extractor, Computer comparator) {
        this.extractor = extractor;
        this.comparator = comparator;
    }

    public Computer getComparator() {
        return comparator;
    }

    public Extractor<T> getExtractor() {
        return extractor;
    }

    public String getType() {
        return type;
    }
}
