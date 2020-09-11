package jp.cafebabe.kunai.source.factories;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import jp.cafebabe.kunai.entries.ClassName;
import jp.cafebabe.kunai.entries.Entry;
import jp.cafebabe.kunai.source.DataSource;

public class ClassFileDataSourceFactoryTest {
    private Path path;
    private Path dummyPath;
    private ClassFileDataSourceFactory factory = new ClassFileDataSourceFactory();

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
        assertThat(entries[0].className(), is(new ClassName("sample.hello.HelloWorld")));
        assertThat(entries[0].isClass(), is(true));
    }
}
