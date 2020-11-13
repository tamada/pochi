package jp.cafebabe.birthmarks.rules;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class RuleTest {
    @Test
    public void testToString() {
        Rule rule = new Rule("prefix", "pattern");

        assertThat(rule.toString(), is("PREFIX,pattern"));
    }

    @Test
    public void testPrefixMatching() {
        Rule rule = new Rule("prefix", "pattern");

        assertThat(rule.match("pattern_ok"), is(true));
        assertThat(rule.match("not_prefix_pattern"), is(false));
    }

    @Test
    public void testSuffixMatching() {
        Rule rule = new Rule("suffix", "pattern");

        assertThat(rule.match("pattern_ok"), is(false));
        assertThat(rule.match("not_prefix_pattern"), is(true));
    }

    @Test
    public void testExactMatching() {
        Rule rule = new Rule("exact", "pattern");

        assertThat(rule.match("pattern"), is(true));
        assertThat(rule.match("not_prefix_pattern"), is(false));
    }

    @Test
    public void testRegexMatching() {
        Rule rule = new Rule("regex", "pat+ern");

        assertThat(rule.match("patttttern"), is(true));
        assertThat(rule.match("pattern_ng"), is(false));
    }
}
