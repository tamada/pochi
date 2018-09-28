package jp.cafebabe.pochi.birthmarks.entities;

public class Percentage {
    private long numerator;
    private long denominator;

    public Percentage(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public String toString() {
        return toString("%3.2g%%");
    }

    public String toString(String formatter) {
        return String.format(formatter, 100.0 * numerator /  denominator);
    }
}
