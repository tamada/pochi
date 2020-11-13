package jp.cafebabe.pochi.pairs;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Pair;
import jp.cafebabe.birthmarks.pairs.PairMatcher;
import jp.cafebabe.birthmarks.pairs.PairMatcherType;
import jp.cafebabe.birthmarks.pairs.Streamable;
import jp.cafebabe.pochi.pairs.builders.RoundRobinPairMatcherBuilder;

public class DefaultRoundRobinPairMatcherTest {
    private PairMatcher<String> matcher = new RoundRobinPairMatcherBuilder<String>()
            .build(new Configuration());

    @Test
    public void testMatcheOneList() {
        List<String> source = Arrays.asList("a", "b", "c", "d", "e", "f");

        List<Pair<String>> list = matcher.match(Streamable.wrap(source))
                .collect(Collectors.toList());

        assertThat(matcher.type(), is(new PairMatcherType("RoundRobin")));

        assertThat(matcher.count(Streamable.wrap(source)), is(15L));
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

        assertThat(matcher.count(Streamable.wrap(source1)), is(1L));
        assertThat(matcher.count(Streamable.wrap(source2)), is(3L));
        assertThat(matcher.count(Streamable.wrap(source1), Streamable.wrap(source2)), is(6L));

        assertThat(list.size(), is(6));
        assertThat(list.get( 0), is(new Pair<>("a", "1")));
        assertThat(list.get( 1), is(new Pair<>("a", "2")));
        assertThat(list.get( 2), is(new Pair<>("a", "3")));
        assertThat(list.get( 3), is(new Pair<>("b", "1")));
        assertThat(list.get( 4), is(new Pair<>("b", "2")));
        assertThat(list.get( 5), is(new Pair<>("b", "3")));
    }
}
