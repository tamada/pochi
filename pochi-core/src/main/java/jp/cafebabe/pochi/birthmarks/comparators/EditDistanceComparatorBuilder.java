package jp.cafebabe.pochi.birthmarks.comparators;

import jp.cafebabe.pochi.birthmarks.comparators.Comparator;
import jp.cafebabe.pochi.birthmarks.comparators.ComparatorBuilder;
import jp.cafebabe.pochi.birthmarks.comparators.ComparatorType;
import jp.cafebabe.pochi.birthmarks.config.Configuration;

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
