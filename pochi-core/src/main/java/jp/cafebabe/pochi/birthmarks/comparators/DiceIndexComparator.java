package jp.cafebabe.pochi.birthmarks.comparators;

import java.util.Set;

import jp.cafebabe.pochi.birthmarks.comparators.ComparatorType;
import jp.cafebabe.pochi.birthmarks.comparators.Similarity;
import jp.cafebabe.pochi.birthmarks.config.Configuration;
import jp.cafebabe.pochi.birthmarks.entities.Birthmark;
import jp.cafebabe.pochi.birthmarks.entities.Element;

public class DiceIndexComparator extends IndexComparator {
    public DiceIndexComparator(Configuration config){
        super(new ComparatorType("DiceIndex"), config);
    }

    @Override
    protected Similarity calculate(Birthmark left, Birthmark right) {
        Set<Element> intersection = intersect(left, right);
        int denominator = left.elementCount() + right.elementCount();
        return new Similarity(2.0 * intersection.size() / denominator);
    }
}
