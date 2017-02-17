package com.github.ebis.birthmarks.comparators;

import java.io.Serializable;

public class Threshold implements Serializable{
    private static final long serialVersionUID = -9068711885782138926L;

    public static final Threshold DEFAULT = new Threshold(0.25);

    private double threshold;

    public Threshold(double threshold){
        this.threshold = threshold;
    }

    public boolean isStolen(Similarity similarity){
        return similarity.value >= (1 - threshold);
    }

    public boolean isInconclusive(Similarity similarity){
        return similarity.value < (1 - threshold)
                && similarity.value > threshold;
    }

    public boolean isInnocent(Similarity similarity){
        return similarity.value <= threshold;
    }
}
