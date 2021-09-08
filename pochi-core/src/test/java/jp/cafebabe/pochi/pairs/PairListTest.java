package jp.cafebabe.pochi.pairs;

import jp.cafebabe.birthmarks.config.Configuration;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

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
        assertThat(list.pairOf("a"), is(contains("a")));
        assertThat(list.pairOf("b"), is(contains("b")));
        assertThat(list.pairOf("c"), is(contains("c")));
        assertThat(list.pairOf("d"), is(contains("e")));
        assertThat(list.pairOf("e"), is(contains("d")));
        assertThat(list.pairOf("f").size(), is(0));
    }
}
