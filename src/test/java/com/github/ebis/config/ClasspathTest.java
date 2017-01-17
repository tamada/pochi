package com.github.ebis.config;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.nio.file.Paths;
import java.util.Optional;

import org.junit.Test;

public class ClasspathTest {
    @Test
    public void testBasic() throws Exception{
        Classpath path = new Classpath("unknown/class/path");

        assertThat(path.toString(), is("unknown/class/path"));
        assertThat(path.equals(new Classpath("unknown/class/path")), is(true));
        assertThat(path.equals(new Object()), is(false));

        assertThat(path.toUrl(), is(Optional.of(Paths.get("unknown/class/path").toUri().toURL())));
    }
}
