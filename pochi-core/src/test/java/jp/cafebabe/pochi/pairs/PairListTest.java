package jp.cafebabe.pochi.pairs;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import jp.cafebabe.birthmarks.config.Configuration;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class PairListTest {
    private PairList list;

    @Before
    public void setUp() {
        Configuration config = new Configuration();
        config.put(PairListBuilder.CONFIG_KEY, "/test-resources/test-matching.csv");
        list = PairListBuilder.build(config);
    }

    @Test
    public void testLoadCsv() {
        assertThat(list.pairOf("a"), is(Optional.of("a")));
        assertThat(list.pairOf("b"), is(Optional.of("b")));
        assertThat(list.pairOf("c"), is(Optional.of("c")));
        assertThat(list.pairOf("d"), is(Optional.of("e")));
        assertThat(list.pairOf("e"), is(Optional.of("d")));
        assertThat(list.pairOf("f"), is(Optional.empty()));
    }
}
