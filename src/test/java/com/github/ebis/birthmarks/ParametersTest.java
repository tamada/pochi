package com.github.ebis.birthmarks;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.github.ebis.birthmarks.extractor.Parameters;

public class ParametersTest {
    private Parameters param;

    @Before
    public void setUp() {
        param = new Parameters();

        param.setValue("k1", "v1");
        param.setValue("k2", "v2");
        param.setValue("k3", "v3");
        param.setValue("k4", "v4");
    }

    @Test
    public void testBasic() {
        assertThat(param.getValue("k1"), is("v1"));
        assertThat(param.getValue("k2"), is("v2"));
        assertThat(param.getValue("k3"), is("v3"));
        assertThat(param.getValue("k4"), is("v4"));

        assertThat(param.getSize(), is(4));
    }

    @Test
    public void testClone() {
        Parameters param = this.param.clone();

        assertThat(param.getValue("k1"), is("v1"));
        assertThat(param.getValue("k2"), is("v2"));
        assertThat(param.getValue("k3"), is("v3"));
        assertThat(param.getValue("k4"), is("v4"));

        assertThat(param.getSize(), is(4));
    }

    @Test
    public void testSelfConstructor() {
        Parameters param = new Parameters(this.param);

        assertThat(param.getValue("k1"), is("v1"));
        assertThat(param.getValue("k2"), is("v2"));
        assertThat(param.getValue("k3"), is("v3"));
        assertThat(param.getValue("k4"), is("v4"));

        assertThat(param.getSize(), is(4));
    }
}
