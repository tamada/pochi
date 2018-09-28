package jp.cafebabe.pochi.runner.birthmarks.comparators;

import java.util.HashSet;
import java.util.Set;

import jp.cafebabe.pochi.birthmarks.comparators.AbstractComparator;
import jp.cafebabe.pochi.birthmarks.comparators.ComparatorType;
import jp.cafebabe.pochi.birthmarks.config.Configuration;
import jp.cafebabe.pochi.birthmarks.entities.Birthmark;
import jp.cafebabe.pochi.birthmarks.entities.Element;

public abstract class IndexComparator extends AbstractComparator {
    public IndexComparator(ComparatorType type, Configuration config){
        super(type, config);
    }

    Set<Element> union(Birthmark left, Birthmark right){
        Set<Element> union = new HashSet<>();
        left.forEach(union::add);
        right.forEach(union::add);
        return union;
    }

    Set<Element> intersect(Birthmark left, Birthmark right){
        Set<Element> intersection = new HashSet<>();
        right.filter(left::contains)
            .forEach(intersection::add);
        return intersection;
    }
}
