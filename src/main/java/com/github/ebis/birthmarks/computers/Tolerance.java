package com.github.ebis.birthmarks.computers;

public class Tolerance {
    public static final Tolerance DEFAULT = new Tolerance(1.0E-6);

    private double tolerance;

    public Tolerance(double tolerance){
        this.tolerance = tolerance;
    }

    public boolean accept(double difference){
        return difference <= tolerance;
    }
}
