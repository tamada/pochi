package com.github.ebis.birthmarks.computers;

public class Similarity {
    private double value;

    public Similarity(double similarity){
        this.value = similarity;
    }

    public boolean isCloseTo(Similarity sim, Tolerance tolerance){
        double distance = Math.abs(value - sim.value);
        return tolerance.accept(distance);
    }

    public boolean isCloseTo(Similarity sim){
        return isCloseTo(sim, Tolerance.DEFAULT);
    }

    public boolean isLessThan(double threshold){
        return value < threshold;
    }

    public boolean isGreaterThan(double threshold){
        return value > threshold;
    }

    public String toString(){
        return String.format("%2.3f", value);
    }
}
