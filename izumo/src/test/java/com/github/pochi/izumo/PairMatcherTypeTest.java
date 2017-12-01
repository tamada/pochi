package com.github.pochi.izumo;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PairMatcherTypeTest {
    private PairMatcherType type = new PairMatcherType("Default");

    @Test
    public void testBasic() {
        assertThat(type, is(new PairMatcherType("Default")));
        assertThat(type.hashCode(), is(new PairMatcherType("Default").hashCode()));
        assertThat(type.toString(), is("Default"));

        assertThat(type, is(not(new PairMatcherType("Different"))));
        assertThat(type.hashCode(), is(not(new PairMatcherType("Different").hashCode())));
        assertThat(type, is(not(new Object())));
    }
}
