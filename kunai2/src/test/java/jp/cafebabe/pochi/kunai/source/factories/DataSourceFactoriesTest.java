package jp.cafebabe.pochi.kunai.source.factories;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static jp.cafebabe.pochi.kunai.util.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.Test;

public class DataSourceFactoriesTest {
    private DataSourceFactories factories = new DataSourceFactories();

    @Test
    public void testDirectory() throws Exception{
        Optional<DataSourceFactory> factory = factories.find(Paths.get("target/classes"), FileSystems.getDefault());

        assertThat(factory.isPresent(), is(true));
        assertThat(factory.get(), is(instanceOf(DirectoryDataSourceFactory.class)));
    }

    @Test
    public void testJar() throws Exception{
        Optional<DataSourceFactory> factory = factories.find(Paths.get("target/test-classes/hello/target/hello-1.0-SNAPSHOT.jar"));

        assertThat(factory.isPresent(), is(true));
        assertThat(factory.get(), is(instanceOf(JarFileDataSourceFactory.class)));
    }

    @Test
    public void testNotExistsJar() throws Exception{
        assertThrows(IOException.class, 
                () -> factories.find(Paths.get("not/exists/jar")));
    }

}
