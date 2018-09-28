package jp.cafebabe.pochi.birthmarks.comparators;

import java.io.Serializable;

public class Similarity implements Serializable{
    private static final long serialVersionUID = 5432533004464914833L;

    double value;

    public Similarity(double value){
        this.value = value;
    }

    public boolean isCloseTo(Similarity similarity, double delta){
        return Math.abs(value - similarity.value) < delta;
    }

    public boolean isStolen(Threshold threshold){
        return threshold.isStolen(this);
    }

    public boolean isInconclusive(Threshold threshold){
        return threshold.isInconclusive(this);
    }

    public boolean isInnocent(Threshold threshold){
        return threshold.isInnocent(this);
    }

    @Override
    public String toString(){
        return Double.toString(value);
    }
}
