package com.github.pochi.izumo;

import java.util.stream.Stream;

import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.pairs.Pair;
import com.github.pochi.birthmarks.pairs.PairMatcherType;
import com.github.pochi.birthmarks.pairs.Streamable;

public class RoundRobinPairMatcher<T> extends AbstractPairMatcher<T> {
    public static final PairMatcherType TYPE = new PairMatcherType("RoundRobin");
    public static final PairMatcherType SAME_PAIR_TYPE = new PairMatcherType("RoundRobinWithSamePair");

    private boolean includeSamePair = false;

    public RoundRobinPairMatcher(PairMatcherType type, boolean includeSamePair,
            Configuration config) {
        super(type, config);
        this.includeSamePair = includeSamePair;
    }

    private int firstIndex(){
        if(includeSamePair)
            return 0;
        return 1;
    }

    @Override
    public Stream<Pair<T>> match(Streamable<T> target) {
        Index index = new Index(firstIndex());
        return target.stream()
                .flatMap(item1 -> matchPair(item1, index, target));
    }

    @Override
    public Stream<Pair<T>> match(Streamable<T> target1, Streamable<T> target2) {
        return target1.stream()
                .flatMap(left -> target2.stream()
                        .map(right -> new Pair<>(left, right)));
    }

    private Stream<Pair<T>> matchPair(T baseTarget, Index index, Streamable<T> list){
        return list.stream()
                .skip(index.index())
                .map(target -> new Pair<>(baseTarget, target));
    }
}
