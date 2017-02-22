package com.github.pochi.runner.birthmarks.pairs;

import com.github.pochi.runner.birthmarks.entities.PairMakerType;

public class RoundRobinWithSamePairPairMaker extends RoundRobinPairMaker{
    public RoundRobinWithSamePairPairMaker(){
        super(new PairMakerType("RoundRobinWithSamePair"), true);
    }
}
