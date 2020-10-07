package jp.cafebabe.pochi.comparators;

import java.util.Set;

import jp.cafebabe.birthmarks.comparators.ComparatorType;
import jp.cafebabe.birthmarks.comparators.Similarity;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Element;

public class JaccardIndexComparator extends IndexComparator {
    public JaccardIndexComparator(Configuration config){
        super(new ComparatorType("JaccardIndex"), config);
    }

    @Override
    protected Similarity calculate(Birthmark left, Birthmark right) {
        Set<Element> intersection = intersect(left, right);
        Set<Element> union = union(left, right);
        return new Similarity((1.0 * intersection.size()) / union.size());
    }
}
