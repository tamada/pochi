package jp.cafebabe.pochi.birthmarks.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jp.cafebabe.pochi.birthmarks.rules.Rule;
import jp.cafebabe.pochi.birthmarks.rules.Rules;

public class Configuration {
    private Rules rules = new Rules();

    private Map<String, String> properties = new HashMap<>();

    public Configuration() {
    }

    Configuration(Rules rules, Map<String, String> properties) {
        this.rules = rules;
        this.properties = properties;
    }

    public Optional<ItemValue> property(ItemKey key) {
        return Optional.ofNullable(
                properties.get(key.toString()))
                .map(ItemValue::new);
    }

    public ItemValue property(ItemKey key, ItemValue defaultValue) {
        Optional<ItemValue> value = property(key);
        return value.orElse(defaultValue);
    }

    public boolean match(String targetName) {
        return rules.match(targetName);
    }

    void setProperty(ItemKey key, ItemValue value) {
        properties.put(key.toString(), value.toString());
    }

    public String getProperty(String key, String defaultValue) {
        return property(ItemKey.of(key))
                .map(value -> value.toString())
                .orElse(defaultValue);
    }

    Stream<Item> propertyStream() {
        return properties.entrySet().stream().map(Item::new);
    }

    Stream<Rule> rules() {
        return rules.stream();
    }

    public String toJson() {
        return String.format("{\"rules\":%s,\"properties\":{%s}}", rules.toJson(),
                toPropertiesJson());
    }

    private String toPropertiesJson() {
        return properties.entrySet().stream()
                .map(entry -> String.format("\"%s\":\"%s\"", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(","));
    }
}
