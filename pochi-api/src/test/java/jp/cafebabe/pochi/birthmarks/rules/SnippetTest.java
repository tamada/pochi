package jp.cafebabe.pochi.birthmarks.rules;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Objects;

import org.junit.Test;

public class SnippetTest {
    private Snippet snippet = new Snippet("snippet_value");

    @Test
    public void testToString() {
        assertThat(snippet.toString(), is("snippet_value"));
    }

    @Test
    public void testEquals() {
        assertThat(snippet, is(new Snippet("snippet_value")));
        assertThat(Objects.equals(snippet, new Snippet("snippet_value")), is(true));
    }

    @Test
    public void testNotEquals() {
        assertThat(Objects.equals(snippet, new Snippet("snippet_value2")), is(false));
        assertThat(Objects.equals(snippet, new Object()), is(false));
        assertThat(Objects.equals(snippet, null), is(false));
    }

    @Test
    public void testHashCode() {
        assertThat(snippet.hashCode(), is(new Snippet("snippet_value").hashCode()));
    }
}
