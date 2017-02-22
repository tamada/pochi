package com.github.pochi.runner.birthmarks.pairs;

import com.github.pochi.runner.birthmarks.entities.PairMakerType;

public class DefaultRoundRobinPairMaker extends RoundRobinPairMaker{
    public DefaultRoundRobinPairMaker(){
        super(new PairMakerType("RoundRobin"), false);
    }
}
