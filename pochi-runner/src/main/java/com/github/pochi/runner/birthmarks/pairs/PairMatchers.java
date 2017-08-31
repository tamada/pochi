package com.github.pochi.runner.birthmarks.pairs;

import java.util.stream.Stream;

import com.github.pochi.birthmarks.Tasks;
import com.github.pochi.birthmarks.pairs.PairMatcher;
import com.github.pochi.birthmarks.pairs.PairMatcherType;

public class PairMatchers extends Tasks<PairMatcherType>{

    public PairMatchers(Stream<PairMatcher> stream){
        super(stream);
    }

    public PairMatcherType[] availableTypes(){
        return stream()
                .map(maker -> maker.type())
                .toArray(size -> new PairMatcherType[size]);
    }
}
