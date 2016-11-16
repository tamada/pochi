package com.github.ebis.birthmarks.extractor;

import java.util.HashMap;
import java.util.Map;

public class Parameters implements Cloneable {
    private Map<String, String> parameters = new HashMap<>();

    public Parameters() {
    }

    public Parameters(Parameters param) {
        param.parameters.entrySet().forEach(entry -> {
            setValue(entry.getKey(), entry.getValue());
        });
    }

    @Override
    public Parameters clone() {
        return new Parameters(this);
    }

    public int getSize() {
        return parameters.size();
    }

    public String getValue(String key) {
        return parameters.get(key);
    }

    public void setValue(String key, String value) {
        parameters.put(key, value);
    }
}
