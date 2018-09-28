package jp.cafebabe.pochi.birthmarks.config;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ItemTest {
    private Item item = new Item(ItemKey.of("key"), ItemValue.of("value"));

    @Test
    public void testItem() {
        assertThat(item, is(Item.of("key", "value")));
        assertThat(item, is(not(Item.of("key2", "value"))));
        assertThat(item, is(not(Item.of("key", "value2"))));
        assertThat(item, is(not(Item.of("key2", "value2"))));
        assertThat(item, is(not("key")));
        assertThat(item, is(not(nullValue())));

        assertThat(item.hashCode(), is(Item.of("key", "value").hashCode()));
        assertThat(item.hashCode(), is(not(Item.of("key2", "value3").hashCode())));
    }

    @Test
    public void testKeyAndValue() {
        assertThat(item.key(), is(ItemKey.of("key")));
        assertThat(item.value(), is(ItemValue.of("value")));

        assertThat(item.key(), is(not(ItemKey.of("key2"))));
        assertThat(item.value(), is(not(ItemValue.of("value2"))));

        assertThat(item.key(), is(not(ItemValue.of("key"))));
        assertThat(item.value(), is(not(ItemKey.of("value"))));

        assertThat(item.key(), is(not(ItemKey.of("not.same.key"))));
        assertThat(item.value(), is(not(ItemValue.of("not.same.value"))));

        assertThat(item.key(), is(not(nullValue())));
        assertThat(item.value(), is(not(nullValue())));
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
