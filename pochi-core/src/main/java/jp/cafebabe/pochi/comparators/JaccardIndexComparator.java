package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.Comparator;
import jp.cafebabe.birthmarks.comparators.ComparatorBuilder;
import jp.cafebabe.birthmarks.comparators.ComparatorType;
import jp.cafebabe.birthmarks.comparators.Similarity;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;

import java.util.Set;

public class JaccardIndexComparator extends IndexComparator {
    private static final ComparatorType thisType = ComparatorType.of("JaccardIndex");

    public static final class Builder implements ComparatorBuilder {
        @Override
        public ComparatorType type() {
            return thisType;
        }

        @Override
        public Comparator build(Configuration config) {
            return new JaccardIndexComparator(config);
        }
    }
    public JaccardIndexComparator(Configuration config){
        super(thisType, config);
    }

    @Override
    protected <T> Similarity calculate(Birthmark<T> left, Birthmark<T> right) {
        Set<T> intersection = intersect(left, right);
        Set<T> union = union(left, right);
        return new Similarity((1.0 * intersection.size()) / union.size());
    }
}
