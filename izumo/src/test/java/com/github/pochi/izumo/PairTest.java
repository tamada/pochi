package com.github.pochi.izumo;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PairTest {
    private Pair<String> pair = new Pair<>("left", "right");

    @Test
    public void testPair() {
        assertThat(pair, is(new Pair<>("left", "right")));
        assertThat(pair.hashCode(), is(new Pair<>("left", "right").hashCode()));
        assertThat(pair.left(), is("left"));
        assertThat(pair.right(), is("right"));

        assertThat(pair, is(not(new Pair<>("right", "left"))));
        assertThat(pair, is(not(new Pair<>("left",  "left"))));
        assertThat(pair, is(not(new Pair<>("right", "right"))));
        assertThat(pair, is(not(new Object())));

        assertThat(pair.hashCode(), is(not(new Pair<>("right", "left").hashCode())));
        assertThat(pair.hashCode(), is(not(new Pair<>("left",  "left").hashCode())));
        assertThat(pair.hashCode(), is(not(new Pair<>("right", "right").hashCode())));
    }
}
