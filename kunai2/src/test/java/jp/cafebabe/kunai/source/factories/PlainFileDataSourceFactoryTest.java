package jp.cafebabe.kunai.source.factories;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import jp.cafebabe.kunai.entries.Entry;
import jp.cafebabe.kunai.source.DataSource;

public class PlainFileDataSourceFactoryTest {
    private Path path;
    private Path dummyPath;
    private DataSourceFactory factory = new PlainFileDataSourceFactory();

    @Before
    public void setUp(){
        path = Paths.get("target/test-classes/hello/target/classes/sample/hello/HelloWorld.class");
        dummyPath = Paths.get("target/test-classes/dummy.class");
    }

    @Test
    public void testBasic() throws Exception{
        assertThat(factory.isTarget(path), is(true));
        assertThat(factory.isTarget(dummyPath), is(false));

        DataSource source = factory.build(new File(path.toString()));
        Entry[] entries = source.stream().toArray(size -> new Entry[size]);

        assertThat(entries.length, is(1));
        assertThat(entries[0].endsWith(".class"), is(true));
        assertThat(entries[0].loadFrom(), is(path.toUri()));
        assertThat(entries[0].className(), is(nullValue()));
        assertThat(entries[0].isClass(), is(true));
        assertThat(entries[0].toString(), is(String.format("%s <%s>", path.toUri(), path)));
    }
}
