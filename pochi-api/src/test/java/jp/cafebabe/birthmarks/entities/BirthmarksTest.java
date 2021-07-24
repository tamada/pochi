package jp.cafebabe.birthmarks.entities;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import jp.cafebabe.kunai.entries.ClassName;

public class BirthmarksTest {
    private Birthmarks<String> birthmarks;

    @Before
    public void setUp() throws Exception{
        List<Birthmark<String>> list = new ArrayList<>();
        list.add(new Birthmark<String>(new Metadata(new ClassName("c1"), new URI("source1"), new BirthmarkType("hoge1")),
                new Elements<String>(IntStream.range(1, 2).mapToObj(label -> "e" + label))));
        list.add(new Birthmark<String>(new Metadata(new ClassName("c2"), new URI("source2"), new BirthmarkType("hoge1")),
                new Elements<String>(IntStream.range(1, 4).mapToObj(label -> "e" + label))));
        list.add(new Birthmark<String>(new Metadata(new ClassName("c3"), new URI("source3"), new BirthmarkType("hoge1")),
                new Elements<String>(IntStream.range(1, 6).mapToObj(label -> "e" + label))));
        this.birthmarks = new Birthmarks<>(list.stream());
    }

    @Test
    public void testBasic(){
        List<Birthmark<String>> list = new ArrayList<>();
        birthmarks.forEach(item -> list.add(item));

        assertThat(birthmarks.count(), is(3L));
        assertThat(list.size(), is(3));
        assertThat(list.get(0).metadata().toString(), is("c1,source1,hoge1"));
        assertThat(list.get(1).metadata().toString(), is("c2,source2,hoge1"));
        assertThat(list.get(2).metadata().toString(), is("c3,source3,hoge1"));

        assertThat(birthmarks.find(new ClassName("c1")).count(), is(1L));
        assertThat(birthmarks.find(new ClassName("c5")).count(), is(0L));
    }

    @Test
    public void testBirthmarkType() throws Exception{
        List<Birthmark<String>> list = new ArrayList<>();
        birthmarks.forEach(item -> list.add(item));

        assertThat(list.get(0).className(), is(new ClassName("c1")));
        assertThat(list.get(0).location(), is(new URI("source1")));
        assertThat(list.get(0).type(), is(new BirthmarkType("hoge1")));
    }

    @Test
    public void testAppend() throws Exception{
        Birthmark<String> b1 = new Birthmark<>(new Metadata(new ClassName("o1"), new URI("otherSource"), new BirthmarkType("hoge1")),
                new Elements<>(IntStream.range(1, 7).mapToObj(label -> "e" + label)));

        Birthmarks<String> other = birthmarks.merge(new Birthmarks<>(Stream.of(b1)));

        List<Birthmark<String>> list = other.stream().collect(Collectors.toList());
        assertThat(list.size(), is(4));
        assertThat(list.get(0).metadata().toString(), is("c1,source1,hoge1"));
        assertThat(list.get(1).metadata().toString(), is("c2,source2,hoge1"));
        assertThat(list.get(2).metadata().toString(), is("c3,source3,hoge1"));
        assertThat(list.get(3).metadata().toString(), is("o1,otherSource,hoge1"));
    }
}
