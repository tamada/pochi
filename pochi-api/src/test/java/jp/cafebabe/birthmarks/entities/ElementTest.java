package jp.cafebabe.birthmarks.entities;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ElementTest {
    private Element element = new Element("element1");

    @Test
    public void testBasic(){
        assertThat(element, is(new Element("element1")));
        assertThat(element.toString(), is("element1"));

        assertThat(new Element(element), is(new Element("element1")));

        assertThat(element.equals(new Object()), is(false));
    }

    @Test
    public void testHashCode(){
        assertThat(element.hashCode(), is(new Element("element1").hashCode()));
    }
}
