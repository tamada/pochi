package com.github.ebis.birthmarks.computers;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.github.ebis.birthmarks.Birthmark;
import com.github.ebis.birthmarks.BirthmarkType;
import com.github.ebis.birthmarks.Element;
import com.github.ebis.birthmarks.MutableElements;

public class JaccardIndexComparatorTest {
    private Birthmark<String> b1;
    private Birthmark<String> b2;

    @Before
    public void setUp() throws Exception {
        MutableElements<String> e1 = new MutableElements<>();
        e1.add(new Element<>("e1"));
        e1.add(new Element<>("e2"));
        e1.add(new Element<>("e3"));
        e1.add(new Element<>("e4"));
        e1.add(new Element<>("e5"));
        b1 = new Birthmark<>(new BirthmarkType("sample"), e1);

        MutableElements<String> e2 = new MutableElements<>();
        e2.add(new Element<>("e1"));
        e2.add(new Element<>("e3"));
        e2.add(new Element<>("e5"));
        b2 = new Birthmark<>(new BirthmarkType("sample"), e2);
    }

    @Test
    public void testBasic() throws Exception {
        Computer comp = new JaccardIndexComputer();
        assertThat(comp.<String> compare(b1, b2), is(closeTo(3.0 / 5, 0.0001)));
    }
}
