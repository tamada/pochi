package com.github.ebis.birthmarks.comparators;

import com.github.ebis.birthmarks.entities.Birthmark;
import com.github.ebis.birthmarks.entities.Pair;

public class Comparison {
    private Pair<Birthmark> pair;
    private Similarity similarity;

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
