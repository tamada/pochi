package jp.cafebabe.birthmarks.entities;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class BirthmarkTypeTest {
    private BirthmarkType type = new BirthmarkType("type");

    @Test
    public void testBasic(){
        assertThat(type, is(new BirthmarkType("type")));
        assertThat(type.toString(), is("type"));

        assertThat(type, is(CoreMatchers.not(new BirthmarkType("different type"))));
        assertThat(type, is(not(new Object())));
    }

    @Test
    public void testHashCode(){
        assertThat(type.hashCode(), is(new BirthmarkType("type").hashCode()));
    }
}
