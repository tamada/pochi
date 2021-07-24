package jp.cafebabe.pochi.birthmarks.uc;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.config.ConfigurationBuilder;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.extractors.Extractor;
import jp.cafebabe.kunai.entries.ClassName;
import jp.cafebabe.kunai.source.DataSource;
import jp.cafebabe.kunai.source.factories.DefaultDataSourceFactory;
import org.junit.Ignore;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FUCBirthmarkExtractorTest {
    public Birthmarks<Frequency> extract(String path) throws Exception{
        URL location = getClass().getResource(path);
        Configuration config = new ConfigurationBuilder().configuration();
        Extractor extractor = new FrequenciesUsedClassesExtractorBuilder().build(config);
        DataSource source = new DefaultDataSourceFactory().build(Paths.get(location.toURI()));
        return extractor.extract(source);
    }

    @Test
    public void testBasic() throws Exception{
        Birthmarks<Frequency> birthmarks = extract("/test-resources/HelloWorld.class");

        assertThat(birthmarks.find(new ClassName("HelloWorld")).count(), is(1L));

        List<Birthmark<Frequency>> list = birthmarks.stream().collect(Collectors.toList());
        assertThat(list.size(), is(1));

        List<String> elements = new ArrayList<>();
        list.get(0).forEach(item -> elements.add(item.toString()));

        assertThat(elements.size(), is(4));
        assertThat(elements.get(0), is("java.io.PrintStream=2"));
        assertThat(elements.get(1), is("java.lang.Object=2"));
        assertThat(elements.get(2), is("java.lang.String=2"));
        assertThat(elements.get(3), is("java.lang.System=1"));
    }

    @Test
    public void testBasic2() throws Exception{
        Birthmarks<Frequency> birthmarks = extract("/test-resources/Fibonacci.class");

        assertThat(birthmarks.find(new ClassName("Fibonacci")).count(), is(1L));

        List<Birthmark<Frequency>> list = birthmarks.stream().collect(Collectors.toList());
        assertThat(list.size(), is(1));

        List<String> elements = new ArrayList<>();
        list.get(0).forEach(item -> elements.add(item.toString()));

        assertThat(elements.size(), is(13));
        assertThat(elements.get(0), is("java.io.PrintStream=2"));
        assertThat(elements.get(1), is("java.lang.Integer=5"));
        assertThat(elements.get(2), is("java.lang.Object=5"));
        assertThat(elements.get(3), is("java.lang.String=1"));
        assertThat(elements.get(4), is("java.lang.System=1"));
        assertThat(elements.get(5), is("java.util.Iterator=3"));
        assertThat(elements.get(6), is("java.util.List=5"));
        assertThat(elements.get(7), is("java.util.function.IntFunction=1"));
        assertThat(elements.get(8), is("java.util.function.IntUnaryOperator=1"));
        assertThat(elements.get(9), is("java.util.stream.Collector=2"));
        assertThat(elements.get(10), is("java.util.stream.Collectors=1"));
        assertThat(elements.get(11), is("java.util.stream.IntStream=7"));
        assertThat(elements.get(12), is("java.util.stream.Stream=2"));
    }

    @Test
    public void testBasic3() throws Exception{
        Birthmarks<Frequency> birthmarks = extract("/test-resources/MazeBuilder.class");

        assertThat(birthmarks.find(new ClassName("MazeBuilder")).count(), is(1L));

        List<Birthmark<Frequency>> list = birthmarks.stream().collect(Collectors.toList());
        assertThat(list.size(), is(1));

        List<String> elements = new ArrayList<>();
        list.get(0).forEach(item -> elements.add(item.toString()));

        assertThat(elements.size(), is(6));
        assertThat(elements.get(0), is("java.io.PrintStream=4"));
        assertThat(elements.get(1), is("java.lang.Integer=70"));
        assertThat(elements.get(2), is("java.lang.Object=2"));
        assertThat(elements.get(3), is("java.lang.String=10"));
        assertThat(elements.get(4), is("java.lang.System=2"));
        assertThat(elements.get(5), is("java.util.Random=4"));
    }

    @Test
    public void testBasic4() throws Exception{
        Birthmarks<Frequency> birthmarks = extract("/test-resources/MyServer2.class");

        assertThat(birthmarks.find(new ClassName("MyServer2")).count(), is(1L));

        List<Birthmark<Frequency>> list = birthmarks.stream().collect(Collectors.toList());
        assertThat(list.size(), is(1));

        List<String> elements = new ArrayList<>();
        list.get(0).forEach(item -> elements.add(item.toString()));

        assertThat(elements.size(), is(3));
        assertThat(elements.get(0), is("java.io.IOException=1"));
        assertThat(elements.get(1), is("java.lang.Object=2"));
        assertThat(elements.get(2), is("java.net.ServerSocket=7"));
    }
}
