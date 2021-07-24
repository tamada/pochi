package jp.cafebabe.pochi.birthmarks.uc;

import jp.cafebabe.birthmarks.entities.Elements;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Names {
    private Map<String, Integer> set = new HashMap<>();

    public void add(String name){
        set.put(name, set.getOrDefault(name, 0) + 1);
    }

    public <T> Elements<T> build(BiFunction<String, Integer, T> mapper) {
        return new Elements<T>(set.entrySet().stream()
                .map(entry -> mapper.apply(entry.getKey(), entry.getValue()))
                .sorted());
    }

    public boolean contains(String name){
        return set.containsKey(name);
    }
}
