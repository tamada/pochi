package jp.cafebabe.pochi.birthmarks.uc;

import jp.cafebabe.birthmarks.entities.Elements;

import java.util.HashSet;
import java.util.Set;

public class Names {
    private Set<String> set = new HashSet<>();

    public void add(String name){
        set.add(name);
    }

    public Elements<String> build(){
        return new Elements<String>(set.stream()
                .sorted());
    }

    public boolean contains(String name){
        return set.contains(name);
    }
}
