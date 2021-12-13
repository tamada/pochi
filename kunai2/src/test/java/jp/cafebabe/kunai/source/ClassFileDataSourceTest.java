package jp.cafebabe.kunai.source;

import jp.cafebabe.kunai.entries.ClassName;
import jp.cafebabe.kunai.entries.Entry;
import jp.cafebabe.kunai.source.factories.DataSourceFactory;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ClassFileDataSourceTest {
    private Path path;

    @Before
    public void setUp(){
        path = Paths.get("target/test-classes/hello/target/classes/sample/hello/HelloWorld.class");
    }

    @Test
    public void testDataSource() throws Exception{
        DataSourceFactory factory = DataSourceFactory.instance();

        assertThat(factory.isTarget(path), is(true));

        try(DataSource source = factory.build(path)){
            Entry[] entries = source.stream().toArray(count -> new Entry[count]);
            assertThat(entries.length, is(1));
            assertThat(entries[0].isName("sample/hello/HelloWorld.class"), is(true));
            assertThat(entries[0].isClass(), is(true));
            assertThat(entries[0].className(), is(new ClassName("sample.hello.HelloWorld")));
        }
    }
}
