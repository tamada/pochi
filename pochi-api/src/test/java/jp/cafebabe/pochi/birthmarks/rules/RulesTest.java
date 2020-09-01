package jp.cafebabe.pochi.birthmarks.rules;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class RulesTest {
    private Rules rules;

    @Before
    public void initializeRules() {
        rules = new Rules(Stream.of(new Rule("prefix", "prefix"),
                new Rule("suffix", "suffix"),
                new Rule("exact", "exact"),
                new Rule("regex", "pat+ern")));
    }

    @Test
    public void testPrefixMatching() {
        assertThat(rules.match("prefix_ok"), is(true));
        assertThat(rules.match("not_prefix_pattern"), is(false));
    }

    @Test
    public void testSuffixMatching() {
        assertThat(rules.match("suffix_ng"), is(false));
        assertThat(rules.match("ok_suffix"), is(true));
    }

    @Test
    public void testExactMatching() {
        assertThat(rules.match("exact"), is(true));
        assertThat(rules.match("ng_exact_ng"), is(false));
    }

    @Test
    public void testRegexMatching() {
        assertThat(rules.match("patttttern"), is(true));
        assertThat(rules.match("pattern_ng"), is(false));
    }

    @Test
    public void testEmptyRules() {
        Rules rules = new Rules();

        assertThat(rules.match("pattern"), is(false));
    }
    @Test
    public void testRuleArray() {
        Rule[] ruleArray = rules.stream().toArray(size -> new Rule[size]);

        assertThat(ruleArray.length, is(4));
    }
}
