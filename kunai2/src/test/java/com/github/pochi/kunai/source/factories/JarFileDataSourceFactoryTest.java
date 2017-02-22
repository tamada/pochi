package com.github.pochi.kunai.source.factories;

import static com.github.pochi.kunai.util.Assert.assertThrows;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class JarFileDataSourceFactoryTest {
    private Path jarfile;
    private Path directory;
    private JarFileDataSourceFactory factory = new JarFileDataSourceFactory();

    @Before
    public void setUp(){
        jarfile = Paths.get("target/test-classes/hello/target/hello-1.0-SNAPSHOT.jar");
        directory = Paths.get("target/test-classes/hello/target/classes");
    }

    @Test
    public void testBasic() throws Exception{
        assertThat(factory.isTarget(jarfile), is(true));
        assertThat(factory.isTarget(directory), is(false));

        factory.build(new File(jarfile.toString()));
    }

    @Test
    public void testThrows() throws Exception{
        assertThrows(UnsupportedDataSourceException.class, 
                () -> factory.build(directory));
    }
}
