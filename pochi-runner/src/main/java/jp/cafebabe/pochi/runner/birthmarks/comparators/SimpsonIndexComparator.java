package jp.cafebabe.pochi.runner.birthmarks.comparators;

import java.util.Set;

import jp.cafebabe.pochi.birthmarks.comparators.ComparatorType;
import jp.cafebabe.pochi.birthmarks.comparators.Similarity;
import jp.cafebabe.pochi.birthmarks.config.Configuration;
import jp.cafebabe.pochi.birthmarks.entities.Birthmark;
import jp.cafebabe.pochi.birthmarks.entities.Element;

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
