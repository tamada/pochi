package com.github.ebis.birthmarks;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class BirthmarkTest {
    private Birthmark<String> b1;

    @Before
    public void setUp() throws Exception {
        MutableElements<String> e1 = new MutableElements<>();
        e1.add(new Element<>("e1"));
        e1.add("e5");
        e1.add(new Element<>("e2"));
        e1.add("e4");
        e1.add(new Element<>("e3"));
        e1.add("e6");

        b1 = new Birthmark<>(new BirthmarkType("sample"), e1);
    }

    @Test
    public void testBasic() {
        assertThat(b1.getType(), is(new BirthmarkType("sample")));

        Elements<String> e1 = b1.elements();
        assertThat(e1.size(), is(6));

        Iterator<Element<String>> iterator = e1.iterator();
        assertThat(e1.get(0), is(iterator.next()));
        assertThat(e1.get(1), is(iterator.next()));
        assertThat(e1.get(2), is(iterator.next()));
        assertThat(e1.get(3), is(iterator.next()));
        assertThat(e1.get(4), is(iterator.next()));
        assertThat(e1.get(5), is(iterator.next()));
        assertThat(false, is(iterator.hasNext()));

        String[] values = e1.stream().map(e -> e.getValue()).toArray(String[]::new);
        assertThat(values, is(equalTo(new String[] { "e1", "e5", "e2", "e4", "e3", "e6", })));
    }
}
