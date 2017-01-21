package com.github.kunai.entries;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

public class NameTest {
    private Name name;

    @Before
    public void setUp(){
        name = new Name("Haruaki Tamada");
    }

    @Test
    public void testBasic(){
        assertThat(name, is(new Name("Haruaki Tamada")));
        assertThat(name, is(not(new Name("Nanashi no Gonbe"))));
        assertThat(name, is(not("Haruaki Tamada")));
        assertThat(name, is(not(new Object())));

        assertThat(name.toString(), is("Haruaki Tamada"));
        assertThat(name.name(), is("Haruaki Tamada"));
    }
}
