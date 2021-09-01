package jp.cafebabe.pochi.util;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import java.net.URL;
import java.util.Optional;

public class ResourceFinderTest {
    @Test
    public void testFindNull() {
        Optional<URL> result = ResourceFinder.find(null);
        assertThat(result, is(Optional.empty()));
    }

    @Test
    public void testFindResource() {
        Optional<URL> result = ResourceFinder.find("/test-resources/test-matching.csv");
        assertThat(result.isPresent(), is(true));
    }

    @Test
    public void testFindFile() {
        Optional<URL> result = ResourceFinder.find("target/test-classes/test-resources/test-matching.csv");
        assertThat(result.isPresent(), is(true));
    }
}
