package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.ComparatorType;
import jp.cafebabe.birthmarks.comparators.Similarity;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;

import java.util.Set;

public class JaccardIndexComparator extends IndexComparator {
    public JaccardIndexComparator(Configuration config){
        super(new ComparatorType("JaccardIndex"), config);
    }

    @Override
    protected <T> Similarity calculate(Birthmark<T> left, Birthmark<T> right) {
        Set<T> intersection = intersect(left, right);
        Set<T> union = union(left, right);
        return new Similarity((1.0 * intersection.size()) / union.size());
    }
}
