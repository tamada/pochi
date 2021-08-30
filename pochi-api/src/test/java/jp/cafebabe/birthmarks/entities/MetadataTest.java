package jp.cafebabe.birthmarks.entities;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;

import jp.cafebabe.kunai.entries.ClassName;

public class MetadataTest {
    private Metadata metadata;

    @Before
    public void setUp() throws Exception{
        metadata = new Metadata(new ClassName("test"), new URI("hoge"), BirthmarkType.of("type"));
    }

    @Test
    public void testBasic() throws Exception{
        assertThat(metadata.isSame(new ClassName("test")), is(true));
        assertThat(metadata.isSame(new URI("hoge")), is(true));
        assertThat(metadata.isSame(BirthmarkType.of("type")), is(true));

        assertThat(metadata.className(), is(new ClassName("test")));
        assertThat(metadata.location(), is(new URI("hoge")));
        assertThat(metadata.type(), is(BirthmarkType.of("type")));

        assertThat(metadata.toString(), is("test,hoge,type"));
    }
}
