package jp.cafebabe.birthmarks.pairs;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PairTest {
    private Pair<String> pair = new Pair<>("left", "right");

    @Test
    public void testBasic(){
        assertThat(pair.left(), is("left"));
        assertThat(pair.right(), is("right"));
        assertThat(pair, is(new Pair<>("left", "right")));

        assertThat(pair.toString(), is("<left, right>"));

        assertThat(pair, is(not("<left, right>")));
        assertThat(pair, is(not(new Pair<>("left2", "right"))));
        assertThat(pair, is(not(new Pair<>("left", "right2"))));
        assertThat(pair, is(not(nullValue())));

        assertThat(pair.hashCode(), is(new Pair<>("left", "right").hashCode()));
        assertThat(pair.hashCode(), is(not(new Pair<>("left2", "right2").hashCode())));
    }
}
