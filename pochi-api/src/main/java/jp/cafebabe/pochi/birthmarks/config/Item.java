package jp.cafebabe.pochi.birthmarks.config;

import java.util.Map;
import java.util.Objects;

public class Item {
    private ItemKey key;
    private ItemValue value;

    public Item(ItemKey key, ItemValue value){
        this.key = key;
        this.value = value;
    }

    Item(Map.Entry<String, String> entry){
        this(new ItemKey(entry.getKey()),
                new ItemValue(entry.getValue()));
    }

    public ItemKey key(){
        return key;
    }

    public ItemValue value(){
        return value;
    }

    public static Item of(String key, String value) {
        return new Item(ItemKey.of(key), ItemValue.of(value));
    }

    @Override
    public int hashCode() {
        return Objects.hash(key(), value(), getClass());
    }

    @Override
    public boolean equals(Object other) {
        return other != null
                && Objects.equals(getClass(), other.getClass())
                && Objects.equals(key(), ((Item)other).key())
                && Objects.equals(value(), ((Item)other).value());
    }
}
