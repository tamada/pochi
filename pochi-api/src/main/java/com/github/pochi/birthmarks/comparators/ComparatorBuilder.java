package com.github.pochi.birthmarks.comparators;

import com.github.pochi.birthmarks.TaskBuilder;
import com.github.pochi.birthmarks.config.Configuration;

public interface ComparatorBuilder extends TaskBuilder<ComparatorType> {
    @Override
    ComparatorType type();

    @Override
    Comparator build(Configuration config);
}
