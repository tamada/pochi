package jp.cafebabe.kunai.source.factories;

import static jp.cafebabe.kunai.util.Assert.assertThrows;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class DirectoryDataSourceFactoryTest {
    private Path jarfile;
    private Path directory;
    private DataSourceFactory factory = new DirectoryDataSourceFactory();

    @Before
    public void setUp(){
        jarfile = Paths.get("target/test-classes/hello/target/hello-1.0-SNAPSHOT.jar");
        directory = Paths.get("target/test-classes/hello/target/classes");
    }

    @Test
    public void testBasic() throws Exception{
        assertThat(factory.isTarget(directory), is(true));
        assertThat(factory.isTarget(jarfile), is(false));

        factory.build(new File(directory.toString()));
    }

    @Test
    public void testThrows() throws Exception{
        assertThrows(UnsupportedDataSourceException.class, 
                () -> factory.build(jarfile));
    }
}
