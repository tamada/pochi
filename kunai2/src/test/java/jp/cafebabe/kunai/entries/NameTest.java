package jp.cafebabe.kunai.entries;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
        assertThat(name, is(Matchers.not(new Name("Nanashi no Gonbe"))));
        assertThat(name, is(not("Haruaki Tamada")));
        assertThat(name, is(not(new Object())));

        assertThat(name.toString(), is("Haruaki Tamada"));
        assertThat(name.name(), is("Haruaki Tamada"));
    }
}
