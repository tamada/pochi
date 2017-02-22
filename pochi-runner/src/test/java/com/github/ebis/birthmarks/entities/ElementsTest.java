package com.github.ebis.birthmarks.entities;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ElementsTest {
    private Elements elements;

    @Before
    public void setUp() throws Exception{
        List<Element> list = new ArrayList<>();
        list.add(new Element("element1"));
        list.add(new Element("element2"));
        list.add(new Element("element3"));
        list.add(new Element("element4"));

        elements = new Elements(list.stream());
    }

    @Test
    public void testBasic(){
        List<Element> list = new ArrayList<>();
        elements.forEach(element -> list.add(element));

        assertThat(list.size(), is(4));
        assertThat(list.get(0), is(new Element("element1")));
        assertThat(list.get(1), is(new Element("element2")));
        assertThat(list.get(2), is(new Element("element3")));
        assertThat(list.get(3), is(new Element("element4")));
    }

    @Test
    public void testFilter(){
        List<Element> list = new ArrayList<>();
        elements.forEach(item -> item.equals(new Element("element2")),
                item -> list.add(item));

        assertThat(list.size(), is(1));
        assertThat(list.get(0), is(new Element("element2")));
    }
}
