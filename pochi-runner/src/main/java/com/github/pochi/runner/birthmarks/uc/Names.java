package com.github.pochi.runner.birthmarks.uc;

import java.util.HashSet;
import java.util.Set;

import com.github.pochi.runner.birthmarks.entities.Element;
import com.github.pochi.runner.birthmarks.entities.Elements;

public class Names {
    private Set<String> set = new HashSet<>();

    public void add(String name){
        set.add(name);
    }

    public Elements build(){
        return new Elements(set.stream()
                .sorted()
                .map(name -> new Element(name)));
    }

    public boolean contains(String name){
        return set.contains(name);
    }
}
