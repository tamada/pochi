package com.github.pochi.runner.birthmarks.kgram;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class KGramTest {
    private KGram<Integer> integerKgram;
    private KGram<String> stringKgram;

    @Before
    public void buildKGram(){
        integerKgram = new KGram<>(IntStream.of(1, 2, 3)
                .mapToObj(Integer::new)
                .toArray(Integer[]::new));
        stringKgram = new KGram<>(Stream.of("a", "ab", "abc", "abcd")
                .toArray(String[]::new));
    }

    @Test
    public void testSizeOfKGram(){
        assertThat(integerKgram.size(), is(3));
        assertThat(stringKgram.size(), is(4));
    }

    @Test
    public void testToString(){
        assertThat(integerKgram.toString(), is("1 2 3"));
        assertThat(stringKgram.toString(), is("a ab abc abcd"));
    }

    @Test
    public void testIndex(){
        assertThat(integerKgram.indexOf(0), is(1));
        assertThat(integerKgram.indexOf(1), is(2));
        assertThat(integerKgram.indexOf(2), is(3));

        assertThat(stringKgram.indexOf(0), is("a"));
        assertThat(stringKgram.indexOf(1), is("ab"));
        assertThat(stringKgram.indexOf(2), is("abc"));
        assertThat(stringKgram.indexOf(3), is("abcd"));
    }
}
