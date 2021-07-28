package jp.cafebabe.birthmarks.entities;

public class Frequency extends Couple<String, Integer> implements Comparable<Frequency> {
    private Frequency(String left, Integer right) {
        super(left, right);
    }

    public static Frequency of(String item, Integer frequency) {
        return new Frequency(item, frequency);
    }

    @Override
    public String toString() {
        return String.format("%s=%d", left(), right());
    }

    @Override
    public int compareTo(Frequency other) {
        return left().compareTo(other.left());
    }
}
