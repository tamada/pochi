package jp.cafebabe.birthmarks.entities.elements;

import jp.cafebabe.birthmarks.entities.Elements;
import jp.cafebabe.birthmarks.entities.Visitor;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListElements implements Elements<String> {
    private List<String> list;

    ListElements(Stream<String> stream) {
        list = stream.collect(Collectors.toList());
    }

    @Override
    public void accept(Visitor<String> visitor) {
        list.forEach(element -> visitor.visitElement(element));
    }

    @Override
    public FrequencyElements asFrequencies() {
        return new FrequencyElements(list);
    }

    @Override
    public ListElements asList() {
        return this;
    }

    @Override
    public ListElements asSet() {
        return new ListElements(list.stream().distinct());
    }

    public Elements<String> filter(Predicate<String> elements) {
        return new ListElements(list.stream()
                .filter(elements));
    }

    @Override
    public boolean contains(String key) {
        return list.contains(key);
    }

    @Override
    public Iterator<String> iterator() {
        return list.iterator();
    }

    @Override
    public Stream<String> stream() {
        return list.stream();
    }

    @Override
    public int size() {
        return list.size();
    }

    public Elements<String> merge(Elements<String> other) {
        return new ListElements(Stream.concat(list.stream(), other.stream()));
    }

    public static ListElements of(String... elements) {
        return new ListElements(Arrays.stream(elements));
    }

    public static ListElements of(Stream<String> elements) {
        return new ListElements(elements);
    }

    public static ListElements asSetOf(String... elements) {
        return new ListElements(Arrays.stream(elements)
                .distinct());
    }
}
