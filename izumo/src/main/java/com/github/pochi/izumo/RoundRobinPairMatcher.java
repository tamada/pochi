package com.github.pochi.izumo;

import java.util.stream.Stream;

class RoundRobinPairMatcher<T> extends AbstractPairMatcher<T> {
    private boolean includeSamePair = false;

    RoundRobinPairMatcher(PairMatcherType type, boolean includeSamePair) {
        super(type);
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
