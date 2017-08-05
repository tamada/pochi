package com.github.pochi.runner.birthmarks.pairs;

import com.github.pochi.birthmarks.TaskBuilders;
import com.github.pochi.birthmarks.pairs.PairMakerBuilder;
import com.github.pochi.birthmarks.pairs.PairMakerType;

public class PairMakerBuilders extends TaskBuilders<PairMakerType, PairMakerBuilder>{

    public PairMakerBuilders() {
        super(PairMakerBuilder.class);
        registerBuilders();
    }

    private void registerBuilders() {
        register(new SameNamePairMakerBuilder());
        register(new RoundRobinPairMakerBuilder(true));
        register(new RoundRobinPairMakerBuilder(false));
    }
}
