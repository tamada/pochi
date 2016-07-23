package com.github.ebis;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RuleTest {
    @Test
    public void testContains() throws Exception {
        Rule rule = new Rule(Rule.Type.Contains, "IO");

        assertThat(rule.isFollow("java.io.IOException"), is(true));
        assertThat(rule.isFollow("java.util.List"), is(false));
    }

    @Test
    public void testExactMatch() throws Exception {
        Rule rule = new Rule(Rule.Type.ExactMatch, "IOException");

        assertThat(rule.isFollow("IOException"), is(true));
        assertThat(rule.isFollow("java.io.Exception"), is(false));
    }

    @Test
    public void testPostfix() throws Exception {
        Rule rule = new Rule(Rule.Type.Postfix, "Test");

        assertThat(rule.isFollow("RuleTest"), is(true));
        assertThat(rule.isFollow("WellknownClassManager"), is(false));
    }

    @Test
    public void testPrefix() throws Exception {
        Rule rule = new Rule(Rule.Type.Prefix, "java.io");

        assertThat(rule.isFollow("java.io.IOException"), is(true));
        assertThat(rule.isFollow("java.util.List"), is(false));
    }

    @Test
    public void testRegex() throws Exception {
        Rule rule = new Rule(Rule.Type.Regex, ".*IO.+");

        assertThat(rule.isFollow("IOException"), is(true));
        assertThat(rule.isFollow("java.io.IOException"), is(true));
        assertThat(rule.isFollow("IO"), is(false));
    }

    @Test(expected = NullPointerException.class)
    public void testThrow1() throws Exception {
        new Rule(null, "hoge");
    }

    @Test(expected = NullPointerException.class)
    public void testThrow2() throws Exception {
        new Rule(Rule.Type.Prefix, null);
    }

    @Test(expected = NullPointerException.class)
    public void testThrow3() throws Exception {
        Rule rule = new Rule(Rule.Type.Prefix, "java.io");
        rule.isFollow(null);
    }
}
