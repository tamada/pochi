package com.github.pochi.birthmarks.entities;

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
        metadata = new Metadata(new ClassName("test"), new URI("hoge"), new BirthmarkType("type"));
    }

    @Test
    public void testBasic() throws Exception{
        assertThat(metadata.isSame(new ClassName("test")), is(true));
        assertThat(metadata.isSame(new URI("hoge")), is(true));
        assertThat(metadata.isSame(new BirthmarkType("type")), is(true));

        assertThat(metadata.className(), is(new ClassName("test")));
        assertThat(metadata.location(), is(new URI("hoge")));
        assertThat(metadata.type(), is(new BirthmarkType("type")));

        assertThat(metadata.toString(), is("test,hoge,type"));
    }
}
