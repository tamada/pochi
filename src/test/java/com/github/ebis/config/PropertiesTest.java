package com.github.ebis.config;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PropertiesTest {
    @Test
    public void testBasic(){
        Properties properties = new Properties();
        properties.put(new ItemKey("k"), new ItemValue("4"));
        properties.put(new Item("out", "stdout"));

        assertThat(properties.property(new ItemKey("k")), is(new ItemValue("4")));
        assertThat(properties.property(new ItemKey("out")), is(new ItemValue("stdout")));
        assertThat(properties.contains(new ItemKey("k")), is(true));

        List<Item> list = new ArrayList<>();
        properties.forEach(item -> list.add(item));
        assertThat(list.size(), is(2));
        assertThat(list.get(0).key(),   is(new ItemKey("k")));
        assertThat(list.get(0).value(), is(new ItemValue("4")));
        assertThat(list.get(0).is(new ItemKey("k")), is(true));
        assertThat(list.get(0).is(new ItemValue("4")), is(true));
        assertThat(list.get(0).is(new ItemValue("5")), is(false));

        assertThat(list.get(0).value().equals(new Object()), is(false));

        assertThat(list.get(1).key(),   is(new ItemKey("out")));
        assertThat(list.get(1).value(), is(new ItemValue("stdout")));
        assertThat(list.get(1).is(new ItemKey("out")), is(true));
        assertThat(list.get(1).is(new ItemValue("stdout")), is(true));
    }
}
