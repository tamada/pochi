package com.github.pochi.kunai.util;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.pochi.kunai.sink.DirectoryMaker;

public class HelpersTest {
    @Test
    public void testMeaninglessPathsHelper() throws Exception{
        PathHelper helper = new PathHelper();
        assertThat(helper, is(not(nullValue())));
    }
    @Test
    public void testMeaninglessStreamHelper() throws Exception{
        StreamHelper helper = new StreamHelper();
        assertThat(helper, is(not(nullValue())));
    }

    @Test
    public void testMeaninglessDirectoryMaker() throws Exception{
        DirectoryMaker maker = new DirectoryMaker();
        assertThat(maker, is(not(nullValue())));
    }
}
