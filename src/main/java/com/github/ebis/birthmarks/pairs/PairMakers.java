package com.github.ebis.birthmarks.pairs;

import com.github.ebis.birthmarks.PairMaker;
import com.github.ebis.birthmarks.Services;
import com.github.ebis.birthmarks.entities.PairMakerType;

public class PairMakers extends Services<PairMakerType, PairMaker>{

    public PairMakers(){
        super(PairMaker.class);
    }

    public PairMakerType[] availableTypes(){
        return availableServices()
                .toArray(size -> new PairMakerType[size]);
    }
}
