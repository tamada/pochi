package com.github.pochi.runner.birthmarks.comparators;

import com.github.pochi.birthmarks.comparators.Comparator;
import com.github.pochi.birthmarks.comparators.ComparatorBuilder;
import com.github.pochi.birthmarks.comparators.ComparatorType;
import com.github.pochi.birthmarks.config.Configuration;

public class SimpsonIndexComparatorBuilder implements ComparatorBuilder {
    @Override
    public ComparatorType type() {
        return new ComparatorType("SimpsonIndex");
    }

    @Override
    public Comparator build(Configuration config) {
        return new SimpsonIndexComparator(config);
    }

}
