package jp.cafebabe.pochi.birthmarks.uc;

import jp.cafebabe.birthmarks.entities.Elements;
import jp.cafebabe.birthmarks.entities.Frequency;
import jp.cafebabe.birthmarks.entities.elements.FrequencyElements;
import jp.cafebabe.birthmarks.entities.elements.ListElements;

import java.util.HashMap;
import java.util.Map;

public class Names {
    private Map<String, Integer> map = new HashMap<>();

    public void add(String name){
        map.put(name, map.getOrDefault(name, 0) + 1);
    }

    public ListElements listElements() {
        return Elements.listElements(map.keySet().stream().sorted());
    }

    public FrequencyElements frequencyElements() {
        return Elements.frequencyElements(map.entrySet().stream()
                .map(entry -> Frequency.of(entry.getKey(), entry.getValue())));
    }

    public boolean contains(String name){
        return map.containsKey(name);
    }
}
