package jp.cafebabe.pochi.birthmarks.uc;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.config.ConfigurationBuilder;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.extractors.Extractor;
import jp.cafebabe.kunai.entries.ClassName;
import jp.cafebabe.kunai.source.DataSource;
import jp.cafebabe.kunai.source.factories.DataSourceFactory;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UCBirthmarkExtractorTest {
    public Birthmarks<String> extract(String path) throws Exception{
        URL location = getClass().getResource(path);
        Configuration config = new ConfigurationBuilder().configuration();
        Extractor extractor = new UCBirthmarkExtractorBuilder().build(config);
        DataSource source = DataSourceFactory.instance().build(Paths.get(location.toURI()));
        return extractor.extract(source);
    }

    @Test
    public void testBasic() throws Exception{
        Birthmarks<String> birthmarks = extract("/test-resources/HelloWorld.class");

        assertThat(birthmarks.find(new ClassName("HelloWorld")).count(), is(1L));

        List<Birthmark<String>> list = birthmarks.stream().collect(Collectors.toList());
        assertThat(list.size(), is(1));

        List<String> elements = new ArrayList<>();
        list.get(0).forEach(item -> elements.add(item));

        assertThat(elements.size(), is(4));
        assertThat(elements.get(0), is("java.io.PrintStream"));
        assertThat(elements.get(1), is("java.lang.Object"));
        assertThat(elements.get(2), is("java.lang.String"));
        assertThat(elements.get(3), is("java.lang.System"));
    }

    @Test
    public void testBasic2() throws Exception{
        Birthmarks<String> birthmarks = extract("/test-resources/Fibonacci.class");

        assertThat(birthmarks.find(new ClassName("Fibonacci")).count(), is(1L));

        List<Birthmark<String>> list = birthmarks.stream().collect(Collectors.toList());
        assertThat(list.size(), is(1));

        List<String> elements = new ArrayList<>();
        list.get(0).forEach(item -> elements.add(item));

        assertThat(elements.size(), is(13));
        assertThat(elements.get(0), is("java.io.PrintStream"));
        assertThat(elements.get(1), is("java.lang.Integer"));
        assertThat(elements.get(2), is("java.lang.Object"));
        assertThat(elements.get(3), is("java.lang.String"));
        assertThat(elements.get(4), is("java.lang.System"));
        assertThat(elements.get(5), is("java.util.Iterator"));
        assertThat(elements.get(6), is("java.util.List"));
        assertThat(elements.get(7), is("java.util.function.IntFunction"));
        assertThat(elements.get(8), is("java.util.function.IntUnaryOperator"));
        assertThat(elements.get(9), is("java.util.stream.Collector"));
        assertThat(elements.get(10), is("java.util.stream.Collectors"));
        assertThat(elements.get(11), is("java.util.stream.IntStream"));
        assertThat(elements.get(12), is("java.util.stream.Stream"));
    }

    @Test
    public void testBasic3() throws Exception{
        Birthmarks<String> birthmarks = extract("/test-resources/MazeBuilder.class");

        assertThat(birthmarks.find(new ClassName("MazeBuilder")).count(), is(1L));

        List<Birthmark<String>> list = birthmarks.stream().collect(Collectors.toList());
        assertThat(list.size(), is(1));

        List<String> elements = new ArrayList<>();
        list.get(0).forEach(item -> elements.add(item));

        assertThat(elements.size(), is(6));
        assertThat(elements.get(0), is("java.io.PrintStream"));
        assertThat(elements.get(1), is("java.lang.Integer"));
        assertThat(elements.get(2), is("java.lang.Object"));
        assertThat(elements.get(3), is("java.lang.String"));
        assertThat(elements.get(4), is("java.lang.System"));
        assertThat(elements.get(5), is("java.util.Random"));
    }

    @Test
    public void testBasic4() throws Exception{
        Birthmarks<String> birthmarks = extract("/test-resources/MyServer2.class");

        assertThat(birthmarks.find(new ClassName("MyServer2")).count(), is(1L));

        List<Birthmark<String>> list = birthmarks.stream().collect(Collectors.toList());
        assertThat(list.size(), is(1));

        List<String> elements = new ArrayList<>();
        list.get(0).forEach(item -> elements.add(item));

        assertThat(elements.size(), is(3));
        assertThat(elements.get(0), is("java.io.IOException"));
        assertThat(elements.get(1), is("java.lang.Object"));
        assertThat(elements.get(2), is("java.net.ServerSocket"));
    }
}
