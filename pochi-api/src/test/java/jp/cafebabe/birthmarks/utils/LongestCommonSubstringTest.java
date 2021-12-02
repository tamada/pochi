package jp.cafebabe.birthmarks.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class LongestCommonSubstringTest {
    @Test
    public void basicTest() {
        assertThat(LongestCommonSubstring.of("abcdxyz", "xyzabcd"), is("abcd"));
        assertThat(LongestCommonSubstring.of("abracadabra", "open sesame"), is("a"));
        assertThat(LongestCommonSubstring.of("aaaa", "bbb"), is(""));
    }
}
