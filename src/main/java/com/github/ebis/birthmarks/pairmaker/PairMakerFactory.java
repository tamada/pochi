package com.github.ebis.birthmarks.pairmaker;

public class PairMakerFactory {
    public static enum Type{
        RoundRobin, Specified, Guessed,
    };
    public PairMakerFactory(){
    }

    public PairMaker get(Type type){
    }
}
