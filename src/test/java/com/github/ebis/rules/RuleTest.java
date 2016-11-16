package com.github.ebis.rules;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RuleTest {
    @Test
    public void testContains() throws Exception {
        Rule rule = new ContainsRule("IO");

        assertThat(rule.isMatch("java.io.IOException"), is(true));
        assertThat(rule.isMatch("java.util.List"), is(false));
    }

    @Test
    public void testExactMatch() throws Exception {
        Rule rule = new MatchRule("IOException");

        assertThat(rule.isMatch("IOException"), is(true));
        assertThat(rule.isMatch("java.io.Exception"), is(false));
    }

    @Test
    public void testPostfix() throws Exception {
        Rule rule = new PostfixRule("Test");

        assertThat(rule.isMatch("RuleTest"), is(true));
        assertThat(rule.isMatch("WellknownClassManager"), is(false));
    }

    @Test
    public void testPrefix() throws Exception {
        Rule rule = new PrefixRule("java.io");

        assertThat(rule.isMatch("java.io.IOException"), is(true));
        assertThat(rule.isMatch("java.util.List"), is(false));
    }

    @Test
    public void testRegex() throws Exception {
        Rule rule = new RegexRule(".*IO.+");

        assertThat(rule.isMatch("IOException"), is(true));
        assertThat(rule.isMatch("java.io.IOException"), is(true));
        assertThat(rule.isMatch("IO"), is(false));
    }

    @Test(expected = NullPointerException.class)
    public void testThrow3() throws Exception {
        Rule rule = new PrefixRule("java.io");
        rule.isMatch(null);
    }
}
