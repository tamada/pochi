package com.github.pochi.birthmarks.pairs;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PairTest {
    private Pair<String> pair = new Pair<>("left", "right");

    @Test
    public void testBasic(){
        assertThat(pair.left(), is("left"));
        assertThat(pair.right(), is("right"));
    }
}
