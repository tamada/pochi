package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.*;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.pochi.comparators.algorithms.EditDistanceCalculator;

import java.util.ArrayList;
import java.util.List;

public class EditDistanceComparator extends AbstractComparator {
    private static final ComparatorType thisType = ComparatorType.of("EditDistance");
    public static final class Builder implements ComparatorBuilder {
        @Override
        public ComparatorType type() {
            return thisType;
        }

        @Override
        public Comparator build(Configuration config) {
            return new EditDistanceComparator(config);
        }
    }

    public EditDistanceComparator(Configuration config){
        super(thisType, config);
    }

    @Override
    protected <T> Similarity calculate(Birthmark<T> left, Birthmark<T> right) {
        List<T> leftList = toList(left);
        List<T> rightList = toList(right);
        return new Similarity(calculate(leftList, rightList));
    }

    private <T> List<T> toList(Birthmark<T> birthmark){
        List<T> list = new ArrayList<>();
        birthmark.forEach(list::add);
        return list;
    }

    private <T> double calculate(List<T> left, List<T> right){
        int distance = new EditDistanceCalculator<T>()
                .compute(left, right);
        return 1d - (1.0 * distance / Math.max(left.size(), right.size()));
    }
}
