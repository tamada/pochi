package com.github.pochi.runner.birthmarks.entities;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.pochi.runner.birthmarks.entities.PairMakerType;

public class PairMakerTypeTest {
    private PairMakerType type = new PairMakerType("round");

    @Test
    public void testBasic(){
        assertThat(type, is(new PairMakerType("round")));
        assertThat(type.toString(), is("round"));

        assertThat(type, is(not(new Object())));
    }
}
