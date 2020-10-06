package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.Comparator;
import jp.cafebabe.birthmarks.comparators.ComparatorBuilder;
import jp.cafebabe.birthmarks.comparators.ComparatorType;
import jp.cafebabe.birthmarks.config.Configuration;

public class EditDistanceComparatorBuilder implements ComparatorBuilder {
    @Override
    public ComparatorType type() {
        return new ComparatorType("EditDistance");
    }

    @Override
    public Comparator build(Configuration config) {
        return new EditDistanceComparator(config);
    }

}
