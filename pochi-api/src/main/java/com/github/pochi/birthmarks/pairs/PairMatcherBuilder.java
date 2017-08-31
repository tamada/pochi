package com.github.pochi.birthmarks.pairs;

import com.github.pochi.birthmarks.TaskBuilder;
import com.github.pochi.birthmarks.config.Configuration;

public interface PairMatcherBuilder extends TaskBuilder<PairMatcherType>{

    @Override
    public PairMatcherType type();

    @Override
    public PairMatcher build(Configuration config);
}
