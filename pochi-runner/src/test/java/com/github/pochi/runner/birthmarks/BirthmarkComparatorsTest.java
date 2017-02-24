package com.github.pochi.runner.birthmarks;

import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.pochi.runner.birthmarks.comparators.ComparatorType;
import com.github.pochi.runner.birthmarks.comparators.DiceIndexBirthmarkComparator;
import com.github.pochi.runner.birthmarks.comparators.EditDistanceBirthmarkComparator;
import com.github.pochi.runner.birthmarks.comparators.JaccardIndexBirthmarkComparator;
import com.github.pochi.runner.birthmarks.comparators.SimpsonIndexBirthmarkComparator;

public class BirthmarkComparatorsTest {
    @Test
    public void testBirthmarkSystem() throws Exception{
        BirthmarkSystem system = new BirthmarkSystem();
        ComparatorType[] types = system.availableComparators();
        assertThat(types.length, is(4));
        assertThat(types, is(arrayContainingInAnyOrder(
                new ComparatorType("DiceIndex"),
                new ComparatorType("JaccardIndex"),
                new ComparatorType("SimpsonIndex"),
                new ComparatorType("EditDistance"))));
        
        BirthmarkComparator comparator1 = system.comparator(new ComparatorType("DiceIndex"));
        assertThat(comparator1, is(instanceOf(BirthmarkComparator.class)));
        assertThat(comparator1, is(instanceOf(DiceIndexBirthmarkComparator.class)));
    }

    @Test
    public void testBasic() throws Exception{
        BirthmarkComparators comparators = new BirthmarkComparators();
        ComparatorType[] types = comparators.availableTypes();

        assertThat(comparators.available(new ComparatorType("DiceIndex")), is(true));
        assertThat(comparators.available(new ComparatorType("unknown")), is(false));

        assertThat(types.length, is(4));
        assertThat(types, is(arrayContainingInAnyOrder(
                new ComparatorType("DiceIndex"),
                new ComparatorType("JaccardIndex"),
                new ComparatorType("SimpsonIndex"),
                new ComparatorType("EditDistance"))));

        BirthmarkComparator comparator1 = comparators.service(new ComparatorType("DiceIndex"));
        assertThat(comparator1, is(instanceOf(BirthmarkComparator.class)));
        assertThat(comparator1, is(instanceOf(DiceIndexBirthmarkComparator.class)));

        BirthmarkComparator comparator2 = comparators.service(new ComparatorType("JaccardIndex"));
        assertThat(comparator2, is(instanceOf(BirthmarkComparator.class)));
        assertThat(comparator2, is(instanceOf(JaccardIndexBirthmarkComparator.class)));

        BirthmarkComparator comparator3 = comparators.service(new ComparatorType("SimpsonIndex"));
        assertThat(comparator3, is(instanceOf(BirthmarkComparator.class)));
        assertThat(comparator3, is(instanceOf(SimpsonIndexBirthmarkComparator.class)));

        BirthmarkComparator comparator4 = comparators.service(new ComparatorType("EditDistance"));
        assertThat(comparator4, is(instanceOf(BirthmarkComparator.class)));
        assertThat(comparator4, is(instanceOf(EditDistanceBirthmarkComparator.class)));
    }
}
