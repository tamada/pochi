package com.github.ebis;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class WellknownClassManagerTest {
    private Rules manager;

    @Before
    public void setUp() {
        manager = new Rules();
        manager.add(new Rule(Rule.Type.Prefix, "java.io."));
        manager.add(new Rule(Rule.Type.Prefix, "java.util."));
        manager.add(new Rule(Rule.Type.Postfix, "Exception"));
    }

    @Test
    public void testBasic() throws Exception {
        assertThat(manager.anyMatch("java.io.InputStream"), is(true));
        assertThat(manager.anyMatch("java.util.List"), is(true));
        assertThat(manager.anyMatch("java.lang.NullPointerException"), is(true));

        assertThat(manager.anyMatch("java.lang.System"), is(false));
    }
}
