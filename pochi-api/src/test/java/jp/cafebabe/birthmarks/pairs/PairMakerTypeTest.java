package jp.cafebabe.birthmarks.pairs;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class PairMakerTypeTest {
    private PairMatcherType type = new PairMatcherType("round");

    @Test
    public void testBasic(){
        assertThat(type, is(new PairMatcherType("round")));
        assertThat(type.toString(), is("round"));

        assertThat(type, is(not(new Object())));
        assertThat(type, is(not(new PairMatcherType("different"))));

        assertThat(type.hashCode(), is(new PairMatcherType("round").hashCode()));
        assertThat(type.hashCode(), is(not(new PairMatcherType("different").hashCode())));
        assertThat(type.hashCode(), is(not("round".hashCode())));
    }
}
