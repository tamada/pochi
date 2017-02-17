package com.github.ebis.birthmarks.uc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.github.ebis.birthmarks.BirthmarkExtractor;
import com.github.ebis.birthmarks.BirthmarkExtractors;
import com.github.ebis.birthmarks.entities.Birthmark;
import com.github.ebis.birthmarks.entities.BirthmarkType;
import com.github.ebis.birthmarks.entities.Birthmarks;
import com.github.ebis.birthmarks.entities.Element;
import com.github.ebis.birthmarks.entities.Results;
import com.github.ebis.config.ConfigurationBuilder;
import com.github.kunai.entries.ClassName;
import com.github.kunai.source.DataSource;
import com.github.kunai.source.factories.DefaultDataSourceFactory;

public class UCBirthmarkExtractorTest {
    public Results<Birthmarks> extract(String path) throws Exception{
        URL location = getClass().getResource(path);
        BirthmarkExtractor extractor = new BirthmarkExtractors().service(new BirthmarkType("uc"));
        DataSource source = new DefaultDataSourceFactory().build(Paths.get(location.toURI()));
        return extractor.extract(source, new ConfigurationBuilder().configuration());
    }

    @Test
    public void testBasic() throws Exception{
        Results<Birthmarks> set = extract("/resources/HelloWorld.class");
        Birthmarks birthmarks = set.result();

        assertThat(set.isSameType(new BirthmarkType("uc")), is(true));
        assertThat(birthmarks.find(new ClassName("HelloWorld")).isPresent(), is(true));

        List<Birthmark> list = birthmarks.stream().collect(Collectors.toList());
        assertThat(list.size(), is(1));

        List<Element> elements = new ArrayList<>();
        list.get(0).forEach(item -> elements.add(item));

        assertThat(elements.size(), is(4));
        assertThat(elements.get(0), is(new Element("java.io.PrintStream")));
        assertThat(elements.get(1), is(new Element("java.lang.Object")));
        assertThat(elements.get(2), is(new Element("java.lang.String")));
        assertThat(elements.get(3), is(new Element("java.lang.System")));
    }

    @Test
    public void testBasic2() throws Exception{
        Results<Birthmarks> set = extract("/resources/Fibonacci.class");
        Birthmarks birthmarks = set.result();

        assertThat(set.isSameType(new BirthmarkType("uc")), is(true));
        assertThat(birthmarks.find(new ClassName("Fibonacci")).isPresent(), is(true));

        List<Birthmark> list = birthmarks.stream().collect(Collectors.toList());
        assertThat(list.size(), is(1));

        List<Element> elements = new ArrayList<>();
        list.get(0).forEach(item -> elements.add(item));

        assertThat(list.get(0).is(new ClassName("Fibonacci")), is(true));
        assertThat(elements.size(), is(13));
        assertThat(elements.get(0), is(new Element("java.io.PrintStream")));
        assertThat(elements.get(1), is(new Element("java.lang.Integer")));
        assertThat(elements.get(2), is(new Element("java.lang.Object")));
        assertThat(elements.get(3), is(new Element("java.lang.String")));
        assertThat(elements.get(4), is(new Element("java.lang.System")));
        assertThat(elements.get(5), is(new Element("java.util.Iterator")));
        assertThat(elements.get(6), is(new Element("java.util.List")));
        assertThat(elements.get(7), is(new Element("java.util.function.IntFunction")));
        assertThat(elements.get(8), is(new Element("java.util.function.IntUnaryOperator")));
        assertThat(elements.get(9), is(new Element("java.util.stream.Collector")));
        assertThat(elements.get(10), is(new Element("java.util.stream.Collectors")));
        assertThat(elements.get(11), is(new Element("java.util.stream.IntStream")));
        assertThat(elements.get(12), is(new Element("java.util.stream.Stream")));
    }

    @Test
    public void testBasic3() throws Exception{
        Results<Birthmarks> set = extract("/resources/MazeBuilder.class");
        Birthmarks birthmarks = set.result();

        assertThat(set.isSameType(new BirthmarkType("uc")), is(true));
        assertThat(birthmarks.find(new ClassName("MazeBuilder")).isPresent(), is(true));

        List<Birthmark> list = birthmarks.stream().collect(Collectors.toList());
        assertThat(list.size(), is(1));

        List<Element> elements = new ArrayList<>();
        list.get(0).forEach(item -> elements.add(item));

        assertThat(elements.size(), is(6));
        assertThat(elements.get(0), is(new Element("java.io.PrintStream")));
        assertThat(elements.get(1), is(new Element("java.lang.Integer")));
        assertThat(elements.get(2), is(new Element("java.lang.Object")));
        assertThat(elements.get(3), is(new Element("java.lang.String")));
        assertThat(elements.get(4), is(new Element("java.lang.System")));
        assertThat(elements.get(5), is(new Element("java.util.Random")));
    }

    @Test
    public void testBasic4() throws Exception{
        Results<Birthmarks> set = extract("/resources/MyServer2.class");
        Birthmarks birthmarks = set.result();

        assertThat(set.isSameType(new BirthmarkType("uc")), is(true));
        assertThat(set.type(), is(new BirthmarkType("uc")));
        assertThat(birthmarks.find(new ClassName("MyServer2")).isPresent(), is(true));

        List<Birthmark> list = birthmarks.stream().collect(Collectors.toList());
        assertThat(list.size(), is(1));

        List<Element> elements = new ArrayList<>();
        list.get(0).forEach(item -> elements.add(item));

        assertThat(elements.size(), is(3));
        assertThat(elements.get(0), is(new Element("java.io.IOException")));
        assertThat(elements.get(1), is(new Element("java.lang.Object")));
        assertThat(elements.get(2), is(new Element("java.net.ServerSocket")));
    }
}
