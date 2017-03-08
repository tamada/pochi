package com.github.pochi.runner.util;

public enum Unit {
    NANO_SECONDS(1),
    MICRO_SECONDS(1_000),
    MILLI_SECONDS(1_000_000),
    SECONDS(1_000_000_000);

    private int digit;

    private Unit(int digit){
        this.digit = digit;
    }

    public double convert(long sourceDuration, Unit sourceUnit){
        return convert((double)sourceDuration, sourceUnit);
    }

    public double convert(double sourceDuration, Unit sourceUnit){
        if(this == sourceUnit)
            return sourceDuration;
        return (sourceDuration / digit) * sourceUnit.digit;
    }
}
