package com.github.pochi.runner.birthmarks.entities;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.pochi.runner.birthmarks.entities.BirthmarkType;

public class BirthmarkTypeTest {
    private BirthmarkType type = new BirthmarkType("type");

    @Test
    public void testBasic(){
        assertThat(type, is(new BirthmarkType("type")));
        assertThat(type.toString(), is("type"));

        assertThat(type, is(not(new Object())));
    }
}
