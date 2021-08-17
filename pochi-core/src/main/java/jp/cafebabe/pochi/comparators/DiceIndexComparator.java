package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.Comparator;
import jp.cafebabe.birthmarks.comparators.ComparatorBuilder;
import jp.cafebabe.birthmarks.comparators.ComparatorType;
import jp.cafebabe.birthmarks.comparators.Similarity;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;

import java.util.Set;

public class DiceIndexComparator extends IndexComparator {
    private static ComparatorType thisType = ComparatorType.of("DiceIndex");

    public static final class Builder implements ComparatorBuilder {
        @Override
        public ComparatorType type() {
            return thisType;
        }

        @Override
        public Comparator build(Configuration config) {
            return new DiceIndexComparator(config);
        }
    }

    public DiceIndexComparator(Configuration config){
        super(thisType, config);
    }

    @Override
    protected <T> Similarity calculate(Birthmark<T> left, Birthmark<T> right) {
        Set<T> intersection = intersect(left, right);
        int denominator = left.elementCount() + right.elementCount();
        return new Similarity(2.0 * intersection.size() / denominator);
    }
}
