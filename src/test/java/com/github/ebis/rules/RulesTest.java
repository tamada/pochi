package com.github.ebis.rules;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class RulesTest {
    private Rules rules;

    @Before
    public void setUp() {
        rules = new Rules();
        rules.add(new PrefixRule("java.io."));
        rules.add(new PrefixRule("java.util."));
        rules.add(new PostfixRule("Exception"));
    }

    @Test
    public void testBasic() throws Exception {
        assertThat(rules.anyMatch("java.io.InputStream"), is(true));
        assertThat(rules.anyMatch("java.util.List"), is(true));
        assertThat(rules.anyMatch("java.lang.NullPointerException"), is(true));

        assertThat(rules.anyMatch("java.lang.System"), is(false));
    }
}
