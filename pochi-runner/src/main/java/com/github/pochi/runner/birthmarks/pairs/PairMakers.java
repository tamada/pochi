package com.github.pochi.runner.birthmarks.pairs;

import com.github.pochi.runner.birthmarks.PairMaker;
import com.github.pochi.runner.birthmarks.Services;
import com.github.pochi.runner.birthmarks.entities.PairMakerType;

public class PairMakers extends Services<PairMakerType, PairMaker>{

    public PairMakers(){
        super(PairMaker.class);
    }

    public PairMakerType[] availableTypes(){
        return availableServices()
                .toArray(size -> new PairMakerType[size]);
    }
}
