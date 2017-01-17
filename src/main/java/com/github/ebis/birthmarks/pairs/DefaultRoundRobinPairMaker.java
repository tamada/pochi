package com.github.ebis.birthmarks.pairs;

import com.github.ebis.birthmarks.entities.PairMakerType;

public class DefaultRoundRobinPairMaker extends RoundRobinPairMaker{
    public DefaultRoundRobinPairMaker(){
        super(new PairMakerType("RoundRobin"), false);
    }
}
