package com.github.pochi.runner.birthmarks.comparators;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ComparatorTypeTest {

    @Test
    public void testType(){
        ComparatorType type = new ComparatorType("type1");

        assertThat(type, is(new ComparatorType("type1")));
        assertThat(type, is(not(new ComparatorType("type2"))));
        assertThat(type, is(not(new Object())));

        assertThat(type.toString(), is("type1"));
    }
}
