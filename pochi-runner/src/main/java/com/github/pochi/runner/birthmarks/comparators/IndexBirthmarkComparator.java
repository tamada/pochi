package com.github.pochi.runner.birthmarks.comparators;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.pochi.runner.birthmarks.entities.Birthmark;
import com.github.pochi.runner.birthmarks.entities.Element;

public abstract class IndexBirthmarkComparator extends AbstractBirthmarkComparator {
    public IndexBirthmarkComparator(ComparatorType type){
        super(type);
    }

    Set<Element> union(Birthmark left, Birthmark right){
        Set<Element> union = new HashSet<>();
        left.forEach(item -> union.add(item));
        right.forEach(item -> union.add(item));
        return union;
    }

    List<Element> intersect(Birthmark left, Birthmark right){
        List<Element> intersection = new ArrayList<>();
        right.filter(item -> left.contains(item))
        .forEach(item -> intersection.add(item));
        return intersection;
    }
}
