package com.github.pochi.birthmarks.comparators;

import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.pairs.Pair;

public class Comparison {
    private Pair<Birthmark> pair;
    private Similarity similarity;

    public Comparison(Birthmark left, Birthmark right, Similarity similarity){
        this(new Pair<>(left, right), similarity);
    }

    public Comparison(Pair<Birthmark> pair, Similarity similarity){
        this.pair = pair;
        this.similarity = similarity;
    }

    public Birthmark left(){
        return pair.left();
    }

    public Birthmark right(){
        return pair.right();
    }

    public Similarity similarity(){
        return similarity;
    }

    public boolean isInnocent(Threshold threshold){
        return threshold.isInnocent(similarity);
    }

    public boolean isStolen(Threshold threshold){
        return threshold.isStolen(similarity);
    }

    public boolean isInconclusive(Threshold threshold){
        return threshold.isInconclusive(similarity);
    }

    @Override
    public String toString(){
        return String.format("%s,%s,%s", 
                left().className(),
                right().className(), similarity);
    }
}
