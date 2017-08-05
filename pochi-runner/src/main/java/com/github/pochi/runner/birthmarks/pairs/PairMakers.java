package com.github.pochi.runner.birthmarks.pairs;

import java.util.stream.Stream;

import com.github.pochi.birthmarks.Tasks;
import com.github.pochi.birthmarks.pairs.PairMaker;
import com.github.pochi.birthmarks.pairs.PairMakerType;

public class PairMakers extends Tasks<PairMakerType>{

    public PairMakers(Stream<PairMaker> stream){
        super(stream);
    }

    public PairMakerType[] availableTypes(){
        return stream()
                .map(maker -> maker.type())
                .toArray(size -> new PairMakerType[size]);
    }
}
