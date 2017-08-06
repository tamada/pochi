package com.github.pochi.birthmarks.config;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.URL;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;

import com.github.pochi.birthmarks.rules.Rule;

public class ConfigurationTest {
    public Configuration build(Optional<URL> url) throws Exception{
        ConfigurationBuilder builder = new ConfigurationBuilder(url);
        return builder.configuration();
    }

    @Test
    public void testDefaultConfig() throws Exception{
        Configuration config = build(Optional.empty());

        Rule[] rules = config.rules().rules();
        assertThat(rules[0].toString(), is("PREFIX,java."));
        assertThat(rules[1].toString(), is("PREFIX,javax."));
        assertThat(rules[2].toString(), is("PREFIX,org.omg."));
        assertThat(rules[3].toString(), is("PREFIX,org.ietf."));
        assertThat(rules[4].toString(), is("PREFIX,org.w3c."));
        assertThat(rules[5].toString(), is("PREFIX,org.xml.sax."));
        assertThat(rules[6].toString(), is("PREFIX,org.apache."));

        assertThat(config.match("java.lang.String"), is(true));

        Stream<Item> stream = config.properties();
        assertThat(stream.count(), is(1L));
    }

    @Test
    public void testBuildingConfig() throws Exception{
        Configuration config = new Configuration();

        config.setProperty(new ItemKey("k"), new ItemValue("4"));

        assertThat(config.match("java.lang.String"), is(false));
        assertThat(config.match("javax.swing.JLabel"), is(false));

        assertThat(config.property(new ItemKey("k")), is(Optional.of(new ItemValue("4"))));
        assertThat(config.property(new ItemKey("l")), is(Optional.empty()));
        assertThat(config.property(new ItemKey("a"), new ItemValue("0")), is(new ItemValue("0")));
    }
}
