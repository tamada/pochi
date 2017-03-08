package com.github.pochi.runner.birthmarks.rules;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.pochi.runner.birthmarks.rules.Snippet;

public class SnippetTest {
    @Test
    public void testBasic(){
        Snippet snippet = new Snippet("java.");

        assertThat(snippet.value(), is("java."));
        assertThat(snippet.toString(), is("java."));

        assertThat(snippet.equals(new Snippet("java.")),  is(true));
        assertThat(snippet.equals(new Snippet("javax.")), is(false));
        assertThat(snippet.equals(new Object()),          is(false));
    }

    @Test
    public void testHashCode(){
        Snippet snippet1 = new Snippet("java.");
        Snippet snippet2 = new Snippet("javax.");

        assertThat(snippet1.hashCode(), is(new Snippet("java.").hashCode()));
        assertThat(snippet1.hashCode(), is(not(snippet2.hashCode())));
    }
}
