package jp.cafebabe.pochi.izumo;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import jp.cafebabe.pochi.birthmarks.config.Configuration;
import jp.cafebabe.pochi.birthmarks.pairs.Pair;
import jp.cafebabe.pochi.birthmarks.pairs.PairMatcher;
import jp.cafebabe.pochi.birthmarks.pairs.PairMatcherType;
import jp.cafebabe.pochi.birthmarks.pairs.Streamable;
import jp.cafebabe.pochi.izumo.builders.GuessedPairMatcherBuilder;

public class GuessedPairMatcherTest {
    private PairMatcher<String> matcher = new GuessedPairMatcherBuilder<String>()
            .build(new Configuration());

    @Test
    public void testMatcheOneList() {
        List<String> source = Arrays.asList("a", "b", "c", "d", "e", "f");

        List<Pair<String>> list = matcher.match(Streamable.wrap(source))
                .collect(Collectors.toList());

        assertThat(matcher.type(), is(new PairMatcherType("Guessed")));

        assertThat(list.size(), is(6));
        assertThat(list.get( 0), is(new Pair<>("a", "a")));
        assertThat(list.get( 1), is(new Pair<>("b", "b")));
        assertThat(list.get( 2), is(new Pair<>("c", "c")));
        assertThat(list.get( 3), is(new Pair<>("d", "d")));
        assertThat(list.get( 4), is(new Pair<>("e", "e")));
        assertThat(list.get( 5), is(new Pair<>("f", "f")));
    }

    @Test
    public void testMatcheTwoList() {
        List<String> source1 = Arrays.asList("a", "b", "2");
        List<String> source2 = Arrays.asList("1", "2", "3", "a");

        List<Pair<String>> list = matcher.match(Streamable.wrap(source1), Streamable.wrap(source2))
                .collect(Collectors.toList());

        assertThat(list.size(), is(2));
        assertThat(list.get( 0), is(new Pair<>("a", "a")));
        assertThat(list.get( 1), is(new Pair<>("2", "2")));
    }
}
