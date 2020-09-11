package jp.cafebabe.kunai.sink.factories;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Test;

import jp.cafebabe.kunai.sink.ClassFileDataSink;
import jp.cafebabe.kunai.sink.DirectoryDataSink;
import jp.cafebabe.kunai.sink.JarFileDataSink;
import jp.cafebabe.kunai.sink.WarFileDataSink;
import jp.cafebabe.kunai.util.PathHelper;

public class DefaultDataSinkFactoryTest {
    private DataSinkFactory factory = new DefaultDataSinkFactory();

    @Test
    public void testBasic(){
        assertThat(factory.isTarget(Paths.get("hoge.jar")), is(true));
        assertThat(factory.isTarget(Paths.get("hoge.war")), is(true));
        assertThat(factory.isTarget(Paths.get("hoge.class")), is(true));
        assertThat(factory.isTarget(Paths.get("dir")), is(true));
        assertThat(factory.isTarget(null), is(false));
    }

    @Test
    public void testBuiltDataSink() throws Exception{
        assertThat(factory.create(Paths.get("hoge.jar")),   is(instanceOf(JarFileDataSink.class)));
        assertThat(factory.create(Paths.get("hoge.war")),   is(instanceOf(WarFileDataSink.class)));
        assertThat(factory.create(Paths.get("hoge.class")), is(instanceOf(ClassFileDataSink.class)));
        assertThat(factory.create(Paths.get("dir")),        is(instanceOf(DirectoryDataSink.class)));
    }

    @After
    public void tearDown() throws Exception{
        Files.deleteIfExists(Paths.get("hoge.jar"));
        Files.deleteIfExists(Paths.get("hoge.war"));
        Files.deleteIfExists(Paths.get("hoge.class"));
        PathHelper.deleteAll(Paths.get("dir"));
    }
}
