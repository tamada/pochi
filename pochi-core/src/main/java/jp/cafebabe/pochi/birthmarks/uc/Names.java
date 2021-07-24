package jp.cafebabe.pochi.birthmarks.uc;

import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.birthmarks.entities.Elements;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Names {
    private Map<String, Integer> set = new HashMap<>();

    public void add(String name){
        set.put(name, set.getOrDefault(name, 0) + 1);
    }

    public Elements build(){
        return new Elements(set.keySet().stream()
                .sorted()
                .map(Element::new));
    }

    public boolean contains(String name){
        return set.containsKey(name);
    }
}
