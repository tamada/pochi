package com.github.kunai.source;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import com.github.kunai.entries.ClassName;
import com.github.kunai.entries.Entry;
import com.github.kunai.source.factories.DataSourceFactory;
import com.github.kunai.source.factories.DefaultDataSourceFactory;

public class DirectoryDataSourceTest {
    private Path path;

    @Before
    public void setUp(){
        path = Paths.get("target/test-classes/hello/target/classes");
    }

    @Test
    public void testDataSource() throws Exception{
        DataSourceFactory factory = new DefaultDataSourceFactory();

        assertThat(factory.isTarget(path), is(true));

        DataSource source = factory.build(path);

        Entry[] entries = source.stream()
                .sorted(new EntryComparator())
                .toArray(count -> new Entry[count]);
        assertThat(entries.length, is(2));

        assertThat(entries[0].isName("sample/hello/HelloWorld.class"), is(true));
        assertThat(entries[0].isClass(), is(true));
        assertThat(entries[0].getClassName(), is(new ClassName("sample.hello.HelloWorld")));

        assertThat(entries[1].isName("sample/hello/Launcher.class"), is(true));
        assertThat(entries[1].isClass(), is(true));
        assertThat(entries[1].getClassName(), is(new ClassName("sample.hello.Launcher")));
    }
}
