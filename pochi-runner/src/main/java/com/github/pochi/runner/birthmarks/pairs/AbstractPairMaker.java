package com.github.pochi.runner.birthmarks.pairs;

import com.github.pochi.runner.birthmarks.PairMaker;
import com.github.pochi.runner.birthmarks.entities.PairMakerType;

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
