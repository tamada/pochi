package com.github.ebis.config;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.URL;
import java.util.Optional;

import org.junit.Test;

import com.github.ebis.birthmarks.rules.Rule;

public class ConfigurationTest {
    public Configuration build(Optional<URL> url) throws Exception{
        ConfigurationBuilder builder = new ConfigurationBuilder(url);
        return builder.configuration();
    }

    @Test
    public void testDefaultConfig() throws Exception{
        Configuration config = build(Optional.empty());

        Rule[] rules = config.rules();
        assertThat(rules.length, is(7));
        assertThat(rules[0].toString(), is("PREFIX,java."));
        assertThat(rules[1].toString(), is("PREFIX,javax."));
        assertThat(rules[2].toString(), is("PREFIX,org.omg."));
        assertThat(rules[3].toString(), is("PREFIX,org.ietf."));
        assertThat(rules[4].toString(), is("PREFIX,org.w3c."));
        assertThat(rules[5].toString(), is("PREFIX,org.xml.sax."));
        assertThat(rules[6].toString(), is("PREFIX,org.apache."));

        assertThat(config.isSystemName("java.lang.String"), is(true));

        Classpath[] classpaths = config.classpaths();
        assertThat(classpaths.length, is(0));

        Item[] items = config.properties();
        assertThat(items.length, is(0));
    }

    @Test
    public void testBuildingConfig() throws Exception{
        Configuration config = new Configuration();

        config.add(new Rule("prefix", "java."));
        config.add(new Classpath("target/test-classes/resources/"));
        config.put(new ItemKey("k"), new ItemValue("4"));

        assertThat(config.isSystemName("java.lang.String"), is(true));
        assertThat(config.isSystemName("javax.swing.JLabel"), is(false));

        assertThat(config.property(new ItemKey("k")), is(new ItemValue("4")));

        ClassLoader loader = config.classLoader();
        assertThat(loader.loadClass("MazeBuilder").getName(), is("MazeBuilder"));
    }
}
