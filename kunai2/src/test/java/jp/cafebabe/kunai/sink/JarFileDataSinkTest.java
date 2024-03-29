package jp.cafebabe.kunai.sink;

import jp.cafebabe.kunai.entries.Entry;
import jp.cafebabe.kunai.source.DataSource;
import jp.cafebabe.kunai.source.factories.DataSourceFactory;
import org.junit.After;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JarFileDataSinkTest {
    @Test
    public void testCreatedJarFile() throws Exception{
        Path path = Paths.get("target/test-classes/hello/target/classes/");
        try(DataSource source = DataSourceFactory.instance().build(path)){
            try(DataSink sink = new JarFileDataSink(Paths.get("hoge.jar"))){
                sink.consume(source);
            }
        }

        DataSource source = DataSourceFactory.instance().build(Paths.get("hoge.jar"));

        List<Entry> list = new ArrayList<>();
        source.forEach(entry -> list.add(entry));

        assertThat(list.size(), is(2));
    }

    @Test
    public void testJarFromJar() throws Exception{
        Path path = Paths.get("target/test-classes/hello/target/hello-1.0-SNAPSHOT.jar");
        try(DataSource source = DataSourceFactory.instance().build(path)){
            try(DataSink sink = new JarFileDataSink(Paths.get("hoge.jar"))){
                sink.consume(source);
            }
        }

        DataSource source = DataSourceFactory.instance().build(Paths.get("hoge.jar"));

        List<Entry> list = new ArrayList<>();
        source.forEach(entry -> list.add(entry));

        assertThat(list.size(), is(5));
    }

    @After
    public void tearDown() throws Exception{
        Files.deleteIfExists(Paths.get("hoge.jar"));
    }
}
