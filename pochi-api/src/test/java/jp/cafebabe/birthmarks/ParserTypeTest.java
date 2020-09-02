package jp.cafebabe.birthmarks;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

public class ParserTypeTest {
    @Test
    public void testCheckTyep() throws Exception {
        ParserType type1 = new ParserType("type1");

        assertThat(type1.toString(), is("type1"));
    }

    @Test
    public void testNotEquals() {
        ParserType type1 = new ParserType("type1");
        ParserType type2 = new ParserType("type2");
        ParserType type1_2 = new ParserType("type1");

        assertThat(type1, is(Matchers.not(type2)));
        assertThat(type1, is(not("type1")));
        assertThat(type1, is(type1_2));

        assertThat(type1.hashCode(), is(type1_2.hashCode()));
    }
}
