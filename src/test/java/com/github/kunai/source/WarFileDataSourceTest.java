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

public class WarFileDataSourceTest {
    private Path path;

    @Before
    public void setUp(){
        path = Paths.get("target/test-classes/hello/target/hello-1.0-SNAPSHOT.war");
    }

    @Test
    public void testDataSource() throws Exception{
        DataSourceFactory factory = new DefaultDataSourceFactory();

        assertThat(factory.isTarget(path), is(true));

        DataSource source = factory.build(path);

        Entry[] entries = source.stream()
                .sorted(new EntryComparator())
                .toArray(count -> new Entry[count]);
        assertThat(entries.length, is(7));

        assertThat(entries[0].isName("META-INF/MANIFEST.MF"), is(true));
        assertThat(entries[0].isClass(), is(false));

        assertThat(entries[1].isName("META-INF/maven/sample/hello/pom.properties"), is(true));
        assertThat(entries[1].isClass(), is(false));

        assertThat(entries[2].isName("META-INF/maven/sample/hello/pom.xml"), is(true));
        assertThat(entries[2].isClass(), is(false));

        assertThat(entries[3].isName("WEB-INF/classes/sample/hello/HelloWorld.class"), is(true));
        assertThat(entries[3].isClass(), is(true));
        assertThat(entries[3].className(), is(new ClassName("sample.hello.HelloWorld")));

        assertThat(entries[4].isName("WEB-INF/classes/sample/hello/Launcher.class"), is(true));
        assertThat(entries[4].isClass(), is(true));
        assertThat(entries[4].className(), is(new ClassName("sample.hello.Launcher")));

        assertThat(entries[5].isName("WEB-INF/web.xml"), is(true));
        assertThat(entries[5].isClass(), is(false));

        assertThat(entries[6].isName("index.html"), is(true));
        assertThat(entries[6].isClass(), is(false));
    }
}
