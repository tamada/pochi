package com.github.kunai.entries;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

public class ClassNameTest {
    private ClassName name1;
    private ClassName name2;

    @Before
    public void setUp(){
        name1 = new ClassName("com.github.kunai.entries.ClassNameTest");
        name2 = new ClassName("com/github/kunai/entries/NameTest");
    }

    @Test
    public void testBasic(){
        assertThat(name1, is(new Name("com.github.kunai.entries.ClassNameTest")));
        assertThat(name1, is(not("com.github.kunai.entries.ClassNameTest")));
        assertThat(name1.toString(), is("com.github.kunai.entries.ClassNameTest"));

        assertThat(name2, is(new Name("com.github.kunai.entries.NameTest")));
        assertThat(name2, is(not("com.github.kunai.entries.NameTest")));
        assertThat(name2.toString(), is("com.github.kunai.entries.NameTest"));
    }
}
