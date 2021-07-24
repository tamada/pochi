package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.ComparatorType;
import jp.cafebabe.birthmarks.comparators.Similarity;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;

import java.util.Set;

public class DiceIndexComparator extends IndexComparator {
    public DiceIndexComparator(Configuration config){
        super(new ComparatorType("DiceIndex"), config);
    }

    @Override
    protected <T> Similarity calculate(Birthmark<T> left, Birthmark<T> right) {
        Set<T> intersection = intersect(left, right);
        int denominator = left.elementCount() + right.elementCount();
        return new Similarity(2.0 * intersection.size() / denominator);
    }
}
