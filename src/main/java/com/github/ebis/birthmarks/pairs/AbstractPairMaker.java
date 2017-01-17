package com.github.ebis.birthmarks.pairs;

import com.github.ebis.birthmarks.PairMaker;
import com.github.ebis.birthmarks.entities.PairMakerType;

public abstract class AbstractPairMaker implements PairMaker{
    private PairMakerType type;

    public AbstractPairMaker(PairMakerType type){
        this.type = type;
    }

    @Override
    public final PairMakerType type(){
        return type;
    }
}
