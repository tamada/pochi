package com.github.ebis.birthmarks;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ElementTest {
    private Element<String> e1;

    @Before
    public void setUp() throws Exception {
        e1 = new Element<>("e1");
    }

    @Test
    public void testBasic() throws Exception {
        Element<String> anotherE1 = new Element<>("e1");

        assertThat(e1.getValue(), is("e1"));
        assertThat(e1, is(anotherE1));

        assertThat("e1", is(e1.toString()));
        assertThat("e1".hashCode(), is(e1.hashCode()));
    }

    @Test
    public void testNotEquals() throws Exception {
        Element<String> notE1 = new Element<>("notE1");

        assertThat(e1, is(not(notE1)));
        assertThat(e1, is(not("notE1")));
    }
}
