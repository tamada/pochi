package jp.cafebabe.pochi.comparators;

import java.util.ArrayList;
import java.util.List;

import jp.cafebabe.birthmarks.comparators.AbstractComparator;
import jp.cafebabe.birthmarks.comparators.ComparatorType;
import jp.cafebabe.birthmarks.comparators.Similarity;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.pochi.comparators.algorithms.EditDistanceCalculator;

public class EditDistanceComparator extends AbstractComparator {
    private EditDistanceCalculator<Element> calculator = new EditDistanceCalculator<>();

    public EditDistanceComparator(Configuration config){
        super(new ComparatorType("EditDistance"), config);
    }

    @Override
    protected Similarity calculate(Birthmark left, Birthmark right) {
        List<Element> leftList = toList(left);
        List<Element> rightList = toList(right);
        return new Similarity(calculate(leftList, rightList));
    }

    private List<Element> toList(Birthmark birthmark){
        List<Element> list = new ArrayList<>();
        birthmark.forEach(list::add);
        return list;
    }

    private double calculate(List<Element> left, List<Element> right){
        int distance = calculator.compute(left, right);
        return 1d - (1.0 * distance / Math.max(left.size(), right.size()));
    }
}
