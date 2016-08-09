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

public class ClassFileDataSourceTest {
    private Path path;

    @Before
    public void setUp(){
        path = Paths.get("target/test-classes/hello/target/classes/sample/hello/HelloWorld.class");
    }

    @Test
    public void testDataSource() throws Exception{
        DataSourceFactory factory = new DefaultDataSourceFactory();

        assertThat(factory.isTarget(path), is(true));

        DataSource source = factory.build(path);

        Entry[] entries = source.stream().toArray(count -> new Entry[count]);
        assertThat(entries.length, is(1));
        assertThat(entries[0].isName("sample/hello/HelloWorld.class"), is(true));
        assertThat(entries[0].isClass(), is(true));
        assertThat(entries[0].getClassName(), is(new ClassName("sample.hello.HelloWorld")));
    }
}
