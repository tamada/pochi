package com.github.pochi.runner.birthmarks.entities;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.pochi.kunai.entries.ClassName;

public class BirthmarkTest {
    private Birthmark birthmark;

    @Before
    public void setUp() throws Exception{
        Metadata metadata = new Metadata(new ClassName("test"), new URI("source1"), new BirthmarkType("type1"));
        Elements elements = new Elements(Arrays.stream(new String[] { "e1", "e2", "e3" })
                .map(string -> new Element(string)));
        this.birthmark = new Birthmark(metadata, elements);
    }

    @Test
    public void testBasic() throws Exception{
        assertThat(birthmark.isSame(new ClassName("test")), is(true));
        assertThat(birthmark.isSame(new BirthmarkType("type1")), is(true));
        assertThat(birthmark.isSame(new URI("source1")), is(true));

        List<Element> list = new ArrayList<>();
        birthmark.forEach(e -> list.add(e));

        assertThat(list.size(), is(3));
        assertThat(list.get(0), is(new Element("e1")));
        assertThat(list.get(1), is(new Element("e2")));
        assertThat(list.get(2), is(new Element("e3")));

        list.clear();

        birthmark.filter(e -> e.equals(new Element("e3"))).forEach(e -> list.add(e));
        assertThat(list.size(), is(1));
        assertThat(list.get(0), is(new Element("e3")));

        Metadata metadata = birthmark.metadata();
        assertThat(metadata.isSame(new ClassName("test")), is(true));
        assertThat(metadata.isSame(new BirthmarkType("type1")), is(true));
        assertThat(metadata.isSame(new URI("source1")), is(true));
        assertThat(metadata.toString(), is("test,source1,type1"));
    }
}
