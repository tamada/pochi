package com.github.pochi.runner.birthmarks.kgram;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class KGramBuilderTest {
    @Test
    public void test2GramBuilder(){
        KGramBuilder<Integer> builder = new KGramBuilder<>(2);

        List<KGram<Integer>> list = builder.build(IntStream
                .of(1, 2, 3, 4, 5, 6, 7)
                .mapToObj(Integer::new)
                .collect(Collectors.toList()))
                .collect(Collectors.toList());

        assertThat(list.size(), is(6));
        assertThat(list.get(0).size(), is(2));
        assertThat(list.get(0).indexOf(0), is(1));
        assertThat(list.get(0).indexOf(1), is(2));
        assertThat(list.get(1).indexOf(0), is(2));
        assertThat(list.get(1).indexOf(1), is(3));
        assertThat(list.get(2).indexOf(0), is(3));
        assertThat(list.get(2).indexOf(1), is(4));
        assertThat(list.get(3).indexOf(0), is(4));
        assertThat(list.get(3).indexOf(1), is(5));
        assertThat(list.get(4).indexOf(0), is(5));
        assertThat(list.get(4).indexOf(1), is(6));
        assertThat(list.get(5).indexOf(0), is(6));
        assertThat(list.get(5).indexOf(1), is(7));
    }

    @Test
    public void test4GramBuilder(){
        KGramBuilder<Integer> builder = new KGramBuilder<>(4);

        List<KGram<Integer>> list = builder.build(IntStream
                .of(1, 2, 3, 4, 5, 6, 7)
                .mapToObj(Integer::new)
                .collect(Collectors.toList()))
                .collect(Collectors.toList());

        assertThat(list.size(), is(4));
        assertThat(list.get(0).size(), is(4));
        assertThat(list.get(0).indexOf(0), is(1));
        assertThat(list.get(0).indexOf(1), is(2));
        assertThat(list.get(0).indexOf(2), is(3));
        assertThat(list.get(0).indexOf(3), is(4));

        assertThat(list.get(1).indexOf(0), is(2));
        assertThat(list.get(1).indexOf(1), is(3));
        assertThat(list.get(1).indexOf(2), is(4));
        assertThat(list.get(1).indexOf(3), is(5));

        assertThat(list.get(2).indexOf(0), is(3));
        assertThat(list.get(2).indexOf(1), is(4));
        assertThat(list.get(2).indexOf(2), is(5));
        assertThat(list.get(2).indexOf(3), is(6));

        assertThat(list.get(3).indexOf(0), is(4));
        assertThat(list.get(3).indexOf(1), is(5));
        assertThat(list.get(3).indexOf(2), is(6));
        assertThat(list.get(3).indexOf(3), is(7));
    }
}
