package com.github.pochi.runner.birthmarks.entities;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;

import com.github.pochi.kunai.entries.ClassName;

public class BirthmarksTest {
    private Birthmarks birthmarks;

    @Before
    public void setUp() throws Exception{
        Birthmark[] array = new Birthmark[3];
        array[0] = new Birthmark(new Metadata(new ClassName("c1"), new URI("source1"), new BirthmarkType("hoge1")),
                new Elements(IntStream.range(1, 2).mapToObj(label -> new Element("e" + label))));
        array[1] = new Birthmark(new Metadata(new ClassName("c2"), new URI("source2"), new BirthmarkType("hoge1")),
                new Elements(IntStream.range(1, 4).mapToObj(label -> new Element("e" + label))));
        array[2] = new Birthmark(new Metadata(new ClassName("c3"), new URI("source3"), new BirthmarkType("hoge1")),
                new Elements(IntStream.range(1, 6).mapToObj(label -> new Element("e" + label))));
        birthmarks = new Birthmarks(Arrays.stream(array));
    }

    @Test
    public void testBasic(){
        List<Birthmark> list = new ArrayList<>();
        birthmarks.forEach(item -> list.add(item));

        assertThat(list.size(), is(3));
        assertThat(list.get(0).metadata().toString(), is("c1,source1,hoge1"));
        assertThat(list.get(1).metadata().toString(), is("c2,source2,hoge1"));
        assertThat(list.get(2).metadata().toString(), is("c3,source3,hoge1"));

        assertThat(birthmarks.find(new ClassName("c1")).isPresent(), is(true));
        assertThat(birthmarks.find(new ClassName("c5")).isPresent(), is(false));
    }

    @Test
    public void testBirthmarkType() throws Exception{
        List<Birthmark> list = new ArrayList<>();
        birthmarks.forEach(item -> list.add(item));

        assertThat(list.get(0).className(), is(new ClassName("c1")));
        assertThat(list.get(0).location(), is(new URI("source1")));
        assertThat(list.get(0).type(), is(new BirthmarkType("hoge1")));
    }

    @Test
    public void testAppend() throws Exception{
        Birthmark b1 = new Birthmark(new Metadata(new ClassName("o1"), new URI("otherSource"), new BirthmarkType("hoge1")),
                new Elements(IntStream.range(1, 7).mapToObj(label -> new Element("e" + label))));

        Birthmarks other = Birthmarks.merge(birthmarks, b1);

        List<Birthmark> list = other.stream().collect(Collectors.toList());
        assertThat(list.size(), is(4));
        assertThat(list.get(0).metadata().toString(), is("c1,source1,hoge1"));
        assertThat(list.get(1).metadata().toString(), is("c2,source2,hoge1"));
        assertThat(list.get(2).metadata().toString(), is("c3,source3,hoge1"));
        assertThat(list.get(3).metadata().toString(), is("o1,otherSource,hoge1"));
    }
}
