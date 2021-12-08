package jp.cafebabe.kunai.entries;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.is;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

public class NameTest {
    private Name name;

    @Before
    public void setUp(){
        name = new Name("Haruaki Tamada");
    }

    @Test
    public void testBasic(){
        assertThat(name, is(new Name("Haruaki Tamada")));
        assertThat(name, is(not(new Name("Nanashi no Gonbe"))));
        assertThat(name, is(not("Haruaki Tamada")));
        assertThat(name, is(not(new Object())));

        assertThat(name.toString(), is("Haruaki Tamada"));
        assertThat(name.name(), is("Haruaki Tamada"));
    }

    @Test
    public void testMatch() {
        assertThat(name.matches("H.*Tamada"), is(true));
        assertThat(name.matches("tamada"), is(false));
        assertThat(name.contains("ki Ta"), is(true));
        assertThat(name.endsWith("a"), is(true));
        assertThat(name.startsWith("H"), is(true));
    }

    @Test
    public void testEquals() {
        assertThat(name.equals(new Name("Haruaki Tamada")), is(true));
        assertThat(name.equals(new Name("Different Name")), is(false));
        assertThat(name.equals(new Object()), is(false));
        assertThat(name.equals(null), is(false));
    }
}
