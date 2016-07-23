package com.github.ebis.birthmarks.computers;

import java.util.HashSet;
import java.util.Set;

import com.github.ebis.birthmarks.Birthmark;

public class DiceIndexComputer implements Computer {
    private static ComputerName TYPE = new ComputerName("DiceIndex");

    @Override
    public <T> double compare(Birthmark<T> b1, Birthmark<T> b2) {
        Set<T> values1 = b1.elements().toSet();
        Set<T> values2 = b2.elements().toSet();

        Set<T> intersection = new HashSet<>(values1);
        intersection.retainAll(values2);

        return 2.0 * intersection.size() / (values1.size() + values2.size());
    }

    @Override
    public ComputerName getName() {
        return TYPE;
    }
}
