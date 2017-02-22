package com.github.pochi.runner.birthmarks.entities;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;

import com.github.pochi.kunai.entries.ClassName;

public class MetadataTest {
    private Metadata metadata;

    @Before
    public void setUp() throws Exception{
        metadata = new Metadata(new URI("hoge"), new ClassName("test"));
    }

    @Test
    public void testBasic(){
        assertThat(metadata.hasSameName(new ClassName("test")), is(true));

        assertThat(metadata.toString(), is("test,hoge"));
    }
}
