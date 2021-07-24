package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.ComparatorType;
import jp.cafebabe.birthmarks.comparators.Similarity;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;

import java.util.Set;

public class SimpsonIndexComparator extends IndexComparator {
    public SimpsonIndexComparator(Configuration config){
        super(new ComparatorType("SimpsonIndex"), config);
    }

    @Override
    protected <T> Similarity calculate(Birthmark<T> left, Birthmark<T> right) {
        Set<T> intersection = intersect(left, right);
        int denominator = Math.min(left.elementCount(), right.elementCount());
        return new Similarity(1.0 * intersection.size() / denominator);
    }
}
