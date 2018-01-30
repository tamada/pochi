package com.github.pochi.birthmarks.entities;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

        assertThat(elements.size(), is(4));
        assertThat(list.size(), is(4));
        assertThat(list.get(0), is(new Element("element1")));
        assertThat(list.get(1), is(new Element("element2")));
        assertThat(list.get(2), is(new Element("element3")));
        assertThat(list.get(3), is(new Element("element4")));
    }

    @Test
    public void testFilter(){
        List<Element> list = new ArrayList<>();
        elements.filter(item -> item.equals(new Element("element2")))
                .forEach(item -> list.add(item));

        assertThat(list.size(), is(1));
        assertThat(list.get(0), is(new Element("element2")));
    }

    @Test
    public void testEmptyElements(){
        Elements elements = Elements.empty();
        assertThat(elements.size(), is(0));
    }

    @Test
    public void testMergeElements(){
        Elements elements2 = new Elements(Stream.of(new Element("element0")));
        Elements elements3 = elements2.merge(elements);

        assertThat(elements3.size(), is(5));
        assertThat(elements3, is(not(sameInstance(elements2))));
        List<Element> list = new ArrayList<>();
        elements3.forEach(list::add);

        assertThat(list.get(0), is(new Element("element0")));
        assertThat(list.get(1), is(new Element("element1")));
        assertThat(list.get(2), is(new Element("element2")));
        assertThat(list.get(3), is(new Element("element3")));
        assertThat(list.get(4), is(new Element("element4")));
    }
}
