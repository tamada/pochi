package com.github.pochi.runner.birthmarks.comparators;

import java.io.Serializable;

public class Threshold implements Serializable{
    private static final long serialVersionUID = -9068711885782138926L;

    public static final Threshold DEFAULT = new Threshold(0.25);

    private double value;

    public Threshold(double threshold){
        this.value = threshold;
    }

    public boolean isStolen(Similarity similarity){
        return similarity.value >= (1 - value);
    }

    public boolean isInconclusive(Similarity similarity){
        return !isStolen(similarity)
                && !isInnocent(similarity);
    }

    public boolean isInnocent(Similarity similarity){
        return similarity.value <= value;
    }
}
