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
    public void testEmptyConfiguration() {
        Configuration config = new ConfigurationBuilder().configuration();
        Rule[] rules = config.rules().rules();

        assertThat(rules.length, is(7));
        assertThat(rules[0].toString(), is("PREFIX,java."));
        assertThat(rules[1].toString(), is("PREFIX,javax."));
        assertThat(rules[2].toString(), is("PREFIX,org.omg."));
        assertThat(rules[3].toString(), is("PREFIX,org.ietf."));
        assertThat(rules[4].toString(), is("PREFIX,org.w3c."));
        assertThat(rules[5].toString(), is("PREFIX,org.xml.sax."));
        assertThat(rules[6].toString(), is("PREFIX,org.apache."));

        assertThat(config.toJson().replace(" \n\r\t", ""), is(
                "{\"rules\":{\"rules\":[" + 
                "{\"type\":\"PREFIX\",\"pattern\":\"java.\"}," + 
                "{\"type\":\"PREFIX\",\"pattern\":\"javax.\"}," + 
                "{\"type\":\"PREFIX\",\"pattern\":\"org.omg.\"}," + 
                "{\"type\":\"PREFIX\",\"pattern\":\"org.ietf.\"}," + 
                "{\"type\":\"PREFIX\",\"pattern\":\"org.w3c.\"}," + 
                "{\"type\":\"PREFIX\",\"pattern\":\"org.xml.sax.\"}," + 
                "{\"type\":\"PREFIX\",\"pattern\":\"org.apache.\"}]}," +
                "\"properties\":{}}")); 
    }

    @Test
    public void testDefaultConfig() throws Exception{
        Configuration config = build(Optional.empty());

        Rule[] rules = config.rules().rules();
        assertThat(rules.length, is(7));
        assertThat(rules[0].toString(), is("PREFIX,java."));
        assertThat(rules[1].toString(), is("PREFIX,javax."));
        assertThat(rules[2].toString(), is("PREFIX,org.omg."));
        assertThat(rules[3].toString(), is("PREFIX,org.ietf."));
        assertThat(rules[4].toString(), is("PREFIX,org.w3c."));
        assertThat(rules[5].toString(), is("PREFIX,org.xml.sax."));
        assertThat(rules[6].toString(), is("PREFIX,org.apache."));

        assertThat(config.match("java.lang.String"), is(true));

        Stream<Item> stream = config.properties();
        assertThat(stream.count(), is(0L));
    }

    @Test
    public void testProperties() throws Exception{
        Configuration config = new Configuration();
        config.setProperty(new ItemKey("k"), new ItemValue("3"));
        config.setProperty(new ItemKey("test-key"), new ItemValue("test-value"));

        Item[] items = config.properties().toArray(Item[]::new);
        assertThat(items[0], is(Item.of("test-key", "test-value")));
        assertThat(items[1], is(Item.of("k", "3")));
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
