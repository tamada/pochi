package jp.cafebabe.birthmarks.entities;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class ElementsTest {
    private Elements<String> elements;

    @Before
    public void setUp() throws Exception{
        elements = Elements.listElements("element1", "element2", "element3", "element4");
    }

    @Test
    public void testBasic(){
        List<String> list = new ArrayList<>();
        elements.forEach(element -> list.add(element));

        assertThat(elements.size(), is(4));
        assertThat(list.size(), is(4));
        assertThat(list.get(0), is("element1"));
        assertThat(list.get(1), is("element2"));
        assertThat(list.get(2), is("element3"));
        assertThat(list.get(3), is("element4"));
    }

    @Test
    public void testFilter(){
        List<String> list = new ArrayList<>();
        elements.filter(item -> item.equals("element2"))
                .forEach(item -> list.add(item));

        assertThat(list.size(), is(1));
        assertThat(list.get(0), is("element2"));
    }

    @Test
    public void testEmptyElements(){
        Elements<String> elements = Elements.listElements();
        assertThat(elements.size(), is(0));
    }

    @Test
    public void testMergeElements(){
        Elements<String> elements2 = Elements.listElements("element0");
        Elements<String> elements3 = elements2.merge(elements);

        assertThat(elements3.size(), is(5));
        assertThat(elements3, is(not(sameInstance(elements2))));
        List<String> list = new ArrayList<>();
        elements3.forEach(list::add);

        assertThat(list.get(0), is("element0"));
        assertThat(list.get(1), is("element1"));
        assertThat(list.get(2), is("element2"));
        assertThat(list.get(3), is("element3"));
        assertThat(list.get(4), is("element4"));
    }
}
