package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.AbstractComparator;
import jp.cafebabe.birthmarks.comparators.ComparatorType;
import jp.cafebabe.birthmarks.comparators.Similarity;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.pochi.comparators.algorithms.EditDistanceCalculator;

import java.util.ArrayList;
import java.util.List;

public class EditDistanceComparator extends AbstractComparator {
    public EditDistanceComparator(Configuration config){
        super(new ComparatorType("EditDistance"), config);
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
