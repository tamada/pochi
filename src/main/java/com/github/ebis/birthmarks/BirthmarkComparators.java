package com.github.ebis.birthmarks;

import com.github.ebis.birthmarks.comparators.ComparatorType;

public class BirthmarkComparators extends Services<ComparatorType, BirthmarkComparator>{
    public BirthmarkComparators(){
        super(BirthmarkComparator.class);
    }

    public ComparatorType[] availableTypes(){
        return availableServices().toArray(size -> new ComparatorType[size]);
    }
}
