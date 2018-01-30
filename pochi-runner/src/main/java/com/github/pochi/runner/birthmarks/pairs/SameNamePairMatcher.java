package com.github.pochi.runner.birthmarks.pairs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.entities.Birthmarks;
import com.github.pochi.birthmarks.pairs.AbstractPairMatcher;
import com.github.pochi.birthmarks.pairs.Pair;
import com.github.pochi.birthmarks.pairs.PairMatcherType;
import com.github.pochi.kunai.entries.ClassName;
import com.github.pochi.runner.util.VectorMap;
import com.github.pochi.runner.util.VectorMap.Entry;

public class SameNamePairMatcher extends AbstractPairMatcher{
    public SameNamePairMatcher(Configuration config){
        super(new PairMatcherType("SameName"), config);
    }
    
    @Override
    public Stream<Pair<Birthmark>> match(Birthmarks birthmarks){
        VectorMap<ClassName, Birthmark> map = new VectorMap<>();
        birthmarks.forEach(birthmark -> putTo(map, birthmark));

        return map.stream().map(Entry::value)
                .filter(list -> list.size() > 1)
                .flatMap(list -> eachPair(list));
    }

    private Stream<Pair<Birthmark>> eachPair(List<Birthmark> list){
        Index index = new Index(1);
        return list.stream().limit(list.size() - 1L)
                .flatMap(birthmark -> list.stream()
                        .skip(index.index())
                        .map(item -> new Pair<>(birthmark, item)));
    }

    private void putTo(VectorMap<ClassName, Birthmark> map, Birthmark birthmark){
        map.put(birthmark.className(), birthmark);
    }

    @Override
    public Stream<Pair<Birthmark>> match(Birthmarks birthmarks1, Birthmarks birthmarks2){
        List<Pair<Birthmark>> list = new ArrayList<>();
        pickUpPairs(list, birthmarks1, birthmarks2);
        return list.stream();
    }

    private void pickUpPairs(List<Pair<Birthmark>> list, Birthmarks birthmarks1, Birthmarks birthmarks2){
        birthmarks1.forEach(item1 -> birthmarks2.stream()
                .filter(item2 -> item1.isSame(item2.className()))
                .forEach(item2 -> list.add(new Pair<>(item1, item2))));
    }
}
