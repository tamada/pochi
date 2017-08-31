package com.github.pochi.birthmarks.pairs;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PairMakerTypeTest {
    private PairMatcherType type = new PairMatcherType("round");

    @Test
    public void testBasic(){
        assertThat(type, is(new PairMatcherType("round")));
        assertThat(type.toString(), is("round"));

        assertThat(type, is(not(new Object())));
    }
}
