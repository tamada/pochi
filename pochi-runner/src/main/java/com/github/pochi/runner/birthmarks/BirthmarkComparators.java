package com.github.pochi.runner.birthmarks;

import com.github.pochi.runner.birthmarks.comparators.ComparatorType;

public class BirthmarkComparators extends Services<ComparatorType, BirthmarkComparator>{
    public BirthmarkComparators(){
        super(BirthmarkComparator.class);
    }

    public ComparatorType[] availableTypes(){
        return availableServices().toArray(size -> new ComparatorType[size]);
    }
}
