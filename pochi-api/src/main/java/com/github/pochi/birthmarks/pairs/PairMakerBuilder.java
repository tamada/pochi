package com.github.pochi.birthmarks.pairs;

import com.github.pochi.birthmarks.TaskBuilder;
import com.github.pochi.birthmarks.config.Configuration;

public interface PairMakerBuilder extends TaskBuilder<PairMakerType>{

    @Override
    public PairMakerType type();

    @Override
    public PairMaker build(Configuration config);
}
