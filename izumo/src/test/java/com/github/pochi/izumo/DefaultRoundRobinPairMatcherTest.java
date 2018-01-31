package com.github.pochi.izumo;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.pairs.Pair;
import com.github.pochi.birthmarks.pairs.PairMatcher;
import com.github.pochi.birthmarks.pairs.PairMatcherType;
import com.github.pochi.birthmarks.pairs.Streamable;
import com.github.pochi.izumo.builders.RoundRobinPairMatcherBuilder;

public class DefaultRoundRobinPairMatcherTest {
    private PairMatcher<String> matcher = new RoundRobinPairMatcherBuilder<String>()
            .build(new Configuration());

    @Test
    public void testMatcheOneList() {
        List<String> source = Arrays.asList("a", "b", "c", "d", "e", "f");

        List<Pair<String>> list = matcher.match(Streamable.wrap(source))
                .collect(Collectors.toList());

        assertThat(matcher.type(), is(new PairMatcherType("RoundRobin")));

        assertThat(list.size(), is(15));
        assertThat(list.get( 0), is(new Pair<>("a", "b")));
        assertThat(list.get( 1), is(new Pair<>("a", "c")));
        assertThat(list.get( 2), is(new Pair<>("a", "d")));
        assertThat(list.get( 3), is(new Pair<>("a", "e")));
        assertThat(list.get( 4), is(new Pair<>("a", "f")));
        assertThat(list.get( 5), is(new Pair<>("b", "c")));
        assertThat(list.get( 6), is(new Pair<>("b", "d")));
        assertThat(list.get( 7), is(new Pair<>("b", "e")));
        assertThat(list.get( 8), is(new Pair<>("b", "f")));
        assertThat(list.get( 9), is(new Pair<>("c", "d")));
        assertThat(list.get(10), is(new Pair<>("c", "e")));
        assertThat(list.get(11), is(new Pair<>("c", "f")));
        assertThat(list.get(12), is(new Pair<>("d", "e")));
        assertThat(list.get(13), is(new Pair<>("d", "f")));
        assertThat(list.get(14), is(new Pair<>("e", "f")));
    }

    @Test
    public void testMatcheTwoList() {
        List<String> source1 = Arrays.asList("a", "b");
        List<String> source2 = Arrays.asList("1", "2", "3");

        List<Pair<String>> list = matcher.match(Streamable.wrap(source1), Streamable.wrap(source2))
                .collect(Collectors.toList());

        assertThat(list.size(), is(6));
        assertThat(list.get( 0), is(new Pair<>("a", "1")));
        assertThat(list.get( 1), is(new Pair<>("a", "2")));
        assertThat(list.get( 2), is(new Pair<>("a", "3")));
        assertThat(list.get( 3), is(new Pair<>("b", "1")));
        assertThat(list.get( 4), is(new Pair<>("b", "2")));
        assertThat(list.get( 5), is(new Pair<>("b", "3")));
    }
}
