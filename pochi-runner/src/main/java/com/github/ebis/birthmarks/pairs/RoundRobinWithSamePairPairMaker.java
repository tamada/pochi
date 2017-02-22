package com.github.ebis.birthmarks.pairs;

import com.github.ebis.birthmarks.entities.PairMakerType;

public class RoundRobinWithSamePairPairMaker extends RoundRobinPairMaker{
    public RoundRobinWithSamePairPairMaker(){
        super(new PairMakerType("RoundRobinWithSamePair"), true);
    }
}
