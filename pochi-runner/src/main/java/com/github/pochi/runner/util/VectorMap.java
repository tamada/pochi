package com.github.pochi.runner.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

public class VectorMap<K, V>{
    private Map<K, List<V>> map = new HashMap<>();

    public Stream<Entry<K, List<V>>> stream(){
        return map.entrySet().stream()
                .map(entry -> new Entry<>(entry.getKey(), entry.getValue()));
    }

    public Stream<Entry<K, V>> flatStream(){
        return map.entrySet().stream()
                .flatMap(this::createStream);
    }

    private Stream<Entry<K, V>> createStream(Map.Entry<K, List<V>> entry){
        return entry.getValue()
                .stream().map(item -> new Entry<>(entry.getKey(), item));
    }

    public void put(K key, V value){
        List<V> list = map.getOrDefault(key, new ArrayList<>());
        list.add(value);
        map.put(key, list);
    }

    public List<V> get(K key){
        return map.get(key);
    }

    public V get(K key, int index){
        Optional<List<V>> list = getOf(key);
        return list.orElseThrow(NoSuchElementException::new)
                .get(index);
    }

    public int size(){
        return map.size();
    }

    private Optional<List<V>> getOf(K key){
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
        return list.map(List::size)
                .orElse(-1);
    }

    public static class Entry<K, V>{
        private K key;
        private V value;

        private Entry(K key, V value){
            this.key = key;
            this.value = value;
        }

        public K key(){
            return key;
        }

        public V value(){
            return value;
        }
    }
}
