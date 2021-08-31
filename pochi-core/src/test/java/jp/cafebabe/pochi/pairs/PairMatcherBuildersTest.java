package jp.cafebabe.pochi.pairs;

import jp.cafebabe.birthmarks.pairs.PairMatcherType;
import jp.cafebabe.pochi.pairs.builders.GuessedPairMatcherBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PairMatcherBuildersTest {
    private PairMatcherBuilders builders;

    @Before
    public void setUp() {
        builders = new PairMatcherBuilders();
    }

    @Test
    public void testBasic() {
        PairMatcherType[] types = builders.availableTypes().toArray(size -> new PairMatcherType[size]);

        assertThat(types.length, is(4));
        assertThat(types[0], is(GuessedPairMatcher.TYPE));
        assertThat(types[1], is(RoundRobinPairMatcher.TYPE));
        assertThat(types[2], is(RoundRobinPairMatcher.SAME_PAIR_TYPE));
        assertThat(types[3], is(SpecifiedPairMatcher.TYPE));
    }

    @Test
    public void testAvailable() {
        assertThat(builders.available(GuessedPairMatcher.TYPE), is(true));
        assertThat(builders.available(RoundRobinPairMatcher.SAME_PAIR_TYPE), is(true));
        assertThat(builders.available(RoundRobinPairMatcher.TYPE), is(true));
        assertThat(builders.available(SpecifiedPairMatcher.TYPE), is(true));
        assertThat(builders.available(NullPairMatcherBuilder.TYPE), is(false));
    }

    @Test
    public void testGetBuilder() {
        assertThat(builders.builder(GuessedPairMatcher.TYPE).getClass().getName(), is(GuessedPairMatcherBuilder.class.getName()));
    }

    @Test
    public void testRegister() {
        builders.register(new NullPairMatcherBuilder());
        assertThat(builders.available(NullPairMatcherBuilder.TYPE), is(true));
    }
}
