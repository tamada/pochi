package com.github.pochi.birthmarks.config;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ItemTest {
    private Item item = new Item(ItemKey.of("key"), ItemValue.of("value"));

    @Test
    public void testKeyAndValue() {
        assertThat(item.key(), is(ItemKey.of("key")));
        assertThat(item.value(), is(ItemValue.of("value")));

        assertThat(item.key(), is(not(ItemValue.of("key"))));
        assertThat(item.value(), is(not(ItemKey.of("value"))));

        assertThat(item.key(), is(not(ItemKey.of("not.same.key"))));
        assertThat(item.value(), is(not(ItemValue.of("not.same.value"))));
    }

    @Test
    public void testHashCode() {
        assertThat(item.key().hashCode(), is(ItemKey.of("key").hashCode()));
        assertThat(item.value().hashCode(), is(ItemValue.of("value").hashCode()));

        assertThat(item.key().hashCode(), is(not(ItemValue.of("key").hashCode())));
        assertThat(item.value().hashCode(), is(not(ItemKey.of("value").hashCode())));

        assertThat(item.key().hashCode(), is(not(ItemKey.of("not.same.key").hashCode())));
        assertThat(item.value().hashCode(), is(not(ItemValue.of("not.same.value").hashCode())));
    }
}
