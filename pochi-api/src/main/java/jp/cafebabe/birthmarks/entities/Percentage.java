package jp.cafebabe.birthmarks.entities;

public class Percentage implements Comparable<Percentage> {
    private long numerator;
    private long denominator;

    public Percentage(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public double value() {
        return (double)numerator / denominator;
    }

    @Override
    public String toString() {
        return toString("%3.2g%%");
    }

    public String toString(String formatter) {
        return String.format(formatter, 100.0 * numerator /  denominator);
    }

    @Override
    public int compareTo(Percentage other) {
        return Double.compare(value(), other.value());
    }
}
