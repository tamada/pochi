package com.github.pochi.runner.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class VectorMap<K, V>{
    private Map<K, List<V>> map = new HashMap<>();

    public Stream<Map.Entry<K, List<V>>> stream(){
        return map.entrySet().stream();
    }

    public void put(K key, V value){
        List<V> list = map.get(key);
        list = putDefaultList(key);
        list.add(value);
    }

    private List<V> putDefaultList(K key){
        List<V> list = new ArrayList<>();
        map.put(key, list);
        return list;
    }

    public List<V> get(K key){
        return map.get(key);
    }

    public V get(K key, int index){
        return get(key).get(index);
    }

    public int size(){
        return map.size();
    }

    public Optional<List<V>> getOf(K key){
        return Optional.ofNullable(map.get(key));
    }

    public List<V> remove(K key){
        return map.remove(key);
    }

    public Optional<V> remove(K key, int index){
        return getOf(key).map(list -> list.remove(index));
    }

    public int size(K key){
        Optional<List<V>> list = getOf(key);
        return list.map(value -> value.size())
                .orElse(-1);
    }
}
