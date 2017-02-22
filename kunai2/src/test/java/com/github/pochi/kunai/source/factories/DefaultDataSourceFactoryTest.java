package com.github.pochi.kunai.source.factories;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class DefaultDataSourceFactoryTest {
    private DataSourceFactory factory = new DefaultDataSourceFactory();
    private Path path = Paths.get("target/test-classes/hello/target/classes");

    @Test
    public void testBasic() throws Exception{
        assertThat(factory.isTarget(path), is(true));
        assertThat(factory.isTarget(null, null, null), is(false));
    }

    @Test
    public void testBasic2() throws Exception{
        assertThat(factory.isTarget(null), is(false));        
    }
}
