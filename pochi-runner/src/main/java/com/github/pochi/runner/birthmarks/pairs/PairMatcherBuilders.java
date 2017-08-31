package com.github.pochi.runner.birthmarks.pairs;

import com.github.pochi.birthmarks.TaskBuilders;
import com.github.pochi.birthmarks.pairs.PairMatcherBuilder;
import com.github.pochi.birthmarks.pairs.PairMatcherType;

public class PairMatcherBuilders extends TaskBuilders<PairMatcherType, PairMatcherBuilder>{

    public PairMatcherBuilders() {
        super(PairMatcherBuilder.class);
        registerBuilders();
    }

    private void registerBuilders() {
        register(new SameNamePairMatcherBuilder());
        register(new RoundRobinPairMatcherBuilder(true));
        register(new RoundRobinPairMatcherBuilder(false));
    }
}
