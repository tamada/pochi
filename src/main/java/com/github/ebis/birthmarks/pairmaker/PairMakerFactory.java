package com.github.ebis.birthmarks.pairmaker;

import java.util.Map;
import java.util.HashMap;

import com.github.kunai.entries.ClassName;

public class PairMakerFactory {
    public static enum Type{
        RoundRobin, Specified, Guessed,
    };
    private Map<Type, PairMaker> makers = new HashMap<>();
        
    public PairMakerFactory(){
        makers.put(Type.RoundRobin, new RoundRobinPairMaker());
        makers.put(Type.Guessed,    new GuessedPairMaker());
    }

    public PairMaker specified(Map<ClassName, ClassName> map){
        return new SpecifiedPairMaker(map);
    }

    public PairMaker get(Type type){
        return makers.get(type);
    }
}
