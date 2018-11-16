package jp.cafebabe.pochi.birthmarks.uc;

import java.util.HashSet;
import java.util.Set;

import jp.cafebabe.pochi.birthmarks.entities.Element;
import jp.cafebabe.pochi.birthmarks.entities.Elements;

public class Names {
    private Set<String> set = new HashSet<>();

    public void add(String name){
        set.add(name);
    }

    public Elements build(){
        return new Elements(set.stream()
                .sorted()
                .map(Element::new));
    }

    public boolean contains(String name){
        return set.contains(name);
    }
}
