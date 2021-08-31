package jp.cafebabe.pochi.pairs;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Pair;
import jp.cafebabe.birthmarks.pairs.PairMatcher;
import jp.cafebabe.birthmarks.pairs.PairMatcherType;
import jp.cafebabe.birthmarks.pairs.Streamable;
import jp.cafebabe.pochi.pairs.builders.SpecifiedPairMatcherBuilder;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SpecifiedPairMatcherTest {
    private PairMatcher<String> matcher = new SpecifiedPairMatcherBuilder<String>()
            .build(new Configuration());

    @Test
    public void testMatcheOneList() {
        List<String> source = Arrays.asList("a", "b", "c", "d", "e", "f");

        List<Pair<String>> list = matcher.match(Streamable.wrap(source))
                .collect(Collectors.toList());

        assertThat(matcher.type(), is(new PairMatcherType("Specified")));
    }

    @Test
    public void testMatcheTwoList() {
        List<String> source1 = Arrays.asList("a", "b", "2");
        List<String> source2 = Arrays.asList("1", "2", "3", "a");

        List<Pair<String>> list = matcher.match(Streamable.wrap(source1), Streamable.wrap(source2))
                .collect(Collectors.toList());

    }
}
