package jp.cafebabe.pochi.comparators;

import java.util.Set;

import jp.cafebabe.birthmarks.comparators.ComparatorType;
import jp.cafebabe.birthmarks.comparators.Similarity;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Element;

public class SimpsonIndexComparator extends IndexComparator {
    public SimpsonIndexComparator(Configuration config){
        super(new ComparatorType("SimpsonIndex"), config);
    }

    @Override
    protected Similarity calculate(Birthmark left, Birthmark right) {
        Set<Element> intersection = intersect(left, right);
        int denominator = Math.min(left.elementCount(), right.elementCount());
        return new Similarity(1.0 * intersection.size() / denominator);
    }
}
