package com.github.pochi.birthmarks.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pochi.birthmarks.rules.Rules;

public class Configuration {
    @JsonProperty("rules")
    private Rules rules = new Rules();

    @JsonProperty("properties")
    private Map<String, String> properties = new HashMap<>();

    public Optional<ItemValue> property(ItemKey key) {
        return Optional.ofNullable(properties.get(key.toString())).map(ItemValue::new);
    }

    public ItemValue property(ItemKey key, ItemValue defaultValue) {
        Optional<ItemValue> value = property(key);
        return value.orElse(defaultValue);
    }

    public boolean match(String targetName) {
        return rules().match(targetName);
    }

    void setProperty(ItemKey key, ItemValue value) {
        properties.put(key.toString(), value.toString());
    }

    Stream<Item> properties() {
        return properties.entrySet().stream().map(Item::new);
    }

    Rules rules() {
        return rules;
    }

    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new InternalError(e);
        }
    }
}
