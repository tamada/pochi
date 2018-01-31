package com.github.pochi.runner.birthmarks.pairs;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.github.pochi.birthmarks.TaskBuilders;
import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.pairs.PairMatcherBuilder;
import com.github.pochi.birthmarks.pairs.PairMatcherType;
import com.github.pochi.izumo.GuessedPairMatcher;
import com.github.pochi.izumo.RoundRobinPairMatcher;
import com.github.pochi.izumo.builders.GuessedPairMatcherBuilder;
import com.github.pochi.izumo.builders.RoundRobinPairMatcherBuilder;

public class PairMatcherBuilders implements TaskBuilders<PairMatcherType, PairMatcherBuilder<Birthmark>>{
    private Map<PairMatcherType, PairMatcherBuilder<Birthmark>> map = new HashMap<>();

    public PairMatcherBuilders() {
        registerBuilders();
    }

    private void registerBuilders() {
        map.put(GuessedPairMatcher.TYPE, new GuessedPairMatcherBuilder<Birthmark>());
        map.put(RoundRobinPairMatcher.SAME_PAIR_TYPE, new RoundRobinPairMatcherBuilder<Birthmark>(true));
        map.put(RoundRobinPairMatcher.TYPE, new RoundRobinPairMatcherBuilder<Birthmark>(false));
    }

    @Override
    public PairMatcherBuilder<Birthmark> builder(PairMatcherType type) {
        return map.get(type);
    }

    @Override
    public boolean available(PairMatcherType type) {
        return map.containsKey(type);
    }

    @Override
    public Stream<PairMatcherType> availableTypes() {
        return map.keySet().stream();
    }
}
