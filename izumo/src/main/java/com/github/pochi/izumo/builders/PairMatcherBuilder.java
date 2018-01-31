package com.github.pochi.izumo.builders;

import com.github.pochi.birthmarks.TaskBuilder;
import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.izumo.PairMatcher;
import com.github.pochi.izumo.PairMatcherType;

public interface PairMatcherBuilder<T> extends TaskBuilder<PairMatcherType>{

    @Override
    public PairMatcherType type();

    @Override
    public PairMatcher<T> build(Configuration config);
}
