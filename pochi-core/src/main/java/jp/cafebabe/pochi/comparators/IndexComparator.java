package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.AbstractComparator;
import jp.cafebabe.birthmarks.comparators.ComparatorType;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;

import java.util.HashSet;
import java.util.Set;

public abstract class IndexComparator extends AbstractComparator {
    public IndexComparator(ComparatorType type, Configuration config){
        super(type, config);
    }

    <T> Set<T> union(Birthmark<T> left, Birthmark<T> right){
        Set<T> union = new HashSet<>();
        left.forEach(union::add);
        right.forEach(union::add);
        return union;
    }

    <T> Set<T> intersect(Birthmark<T> left, Birthmark<T> right){
        Set<T> intersection = new HashSet<>();
        right.filter(left::contains)
            .forEach(intersection::add);
        return intersection;
    }
}
