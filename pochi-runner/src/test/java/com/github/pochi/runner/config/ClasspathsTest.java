package com.github.pochi.runner.config;

import static com.github.pochi.runner.Assert.assertThrows;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;


public class ClasspathsTest {
    @Test
    public void testBasic() throws Exception{
        Classpaths cps = new Classpaths();
        cps.add(new Classpath("target/test-classes/resources"));

        ClassLoader defaultLoader = getClass().getClassLoader();
        assertThrows(ClassNotFoundException.class, () -> defaultLoader.loadClass("Fibonacci"));
        Class<?> clazz1;
        assertThat(clazz1 = defaultLoader.loadClass("com.github.pochi.runner.config.ClasspathsTest"), is(not(nullValue())));
        assertThat(clazz1.getName(), is("com.github.pochi.runner.config.ClasspathsTest"));

        ClassLoader loader1 = cps.buildClassLoader();
        Class<?> clazz2, clazz3;
        assertThat(clazz2 = loader1.loadClass("Fibonacci"), is(not(nullValue())));
        assertThat(clazz2.getName(), is("Fibonacci"));
        assertThat(clazz3 = loader1.loadClass("com.github.pochi.runner.config.ClasspathsTest"), is(not(nullValue())));
        assertThat(clazz3.getName(), is("com.github.pochi.runner.config.ClasspathsTest"));
    }
}
