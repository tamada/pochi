package jp.cafebabe.pochi.birthmarks.uc;

import jp.cafebabe.birthmarks.entities.Elements;

import java.util.HashMap;
import java.util.Map;

public class Names {
    private Map<String, Integer> set = new HashMap<>();

    public void add(String name){
        set.put(name, set.getOrDefault(name, 0) + 1);
    }

    public Elements<String> build(){
        return new Elements<String>(set.keySet().stream()
                .sorted());
    }

    public boolean contains(String name){
        return set.containsKey(name);
    }
}
