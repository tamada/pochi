package jp.cafebabe.kunai.sink;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jp.cafebabe.kunai.entries.Entry;
import jp.cafebabe.kunai.source.DataSource;
import jp.cafebabe.kunai.source.factories.DefaultDataSourceFactory;
import jp.cafebabe.kunai.util.PathHelper;

public class DirectoryDataSinkTest {
    @Before
    public void setUp() throws Exception{
        Path path = Paths.get("target/test-classes/hello/target/hello-1.0-SNAPSHOT.jar");
        try(DataSource source = new DefaultDataSourceFactory().build(path)){
            try(DataSink sink = new DirectoryDataSink(Paths.get("dir"))){
                sink.consume(source);
            }
        }
    }

    @Test
    public void testCreatedDirectory() throws Exception{
        DataSource source = new DefaultDataSourceFactory().build(Paths.get("dir"));

        List<Entry> list = new ArrayList<>();
        source.forEach(entry -> list.add(entry));

        assertThat(list.size(), is(2));
    }

    @After
    public void tearDown() throws Exception{
        PathHelper.deleteAll(Paths.get("dir"));
    }
}
