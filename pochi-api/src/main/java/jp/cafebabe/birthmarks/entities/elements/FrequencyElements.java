package jp.cafebabe.birthmarks.entities.elements;

import jp.cafebabe.birthmarks.entities.Elements;
import jp.cafebabe.birthmarks.entities.Frequency;
import jp.cafebabe.birthmarks.entities.Visitor;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FrequencyElements implements Elements<Frequency> {
    private Map<String, Integer> frequencies;

    FrequencyElements(List<String> elements) {
        frequencies = elements.stream()
                .collect(Collectors.toMap(e -> e, e -> 1, (b, c) -> b + c));
    }

    FrequencyElements(String... elements) {
        frequencies = Arrays.stream(elements)
                .collect(Collectors.toMap(element -> element, e -> 1, (b, c) -> b + c));
    }

    FrequencyElements(Stream<Frequency> stream) {
        frequencies = stream.collect(Collectors.toMap(c -> c.left(), c -> c.right(), (b, c) -> b + c));
    }

    @Override
    public FrequencyElements asFrequencies() {
        return this;
    }

    @Override
    public ListElements asList() {
        return new ListElements(frequencies.keySet()
                .stream());
    }

    @Override
    public ListElements asSet() {
        return new ListElements(frequencies.keySet()
                .stream().distinct());
    }

    public Elements<Frequency> filter(Predicate<Frequency> predicate) {
        return new FrequencyElements(frequencies.entrySet()
                .stream()
                .map(e -> Frequency.of(e.getKey(), e.getValue()))
                .filter(predicate));
    }

    @Override
    public boolean contains(String key) {
        return frequencies.containsKey(key);
    }

    @Override
    public Iterator<Frequency> iterator() {
        Iterator<Map.Entry<String, Integer>> iterator = frequencies.entrySet().iterator();
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Frequency next() {
                return entryToCouple(iterator.next());
            }
        };
    }

    public Stream<Frequency> stream() {
        return frequencies.entrySet()
                .stream()
                .map(entry -> entryToCouple(entry));
    }

    @Override
    public Elements<Frequency> merge(Elements<Frequency> other) {
        return new FrequencyElements(Stream.concat(stream(), other.stream()));
    }

    @Override
    public int size() {
        return 0;
    }

    private Frequency entryToCouple(Map.Entry<String, Integer> entry) {
        return Frequency.of(entry.getKey(), entry.getValue());
    }

    @Override
    public void accept(Visitor<Frequency> visitor) {
        stream().forEach(visitor::visitElement);
    }

    public static FrequencyElements of(String... elements) {
        return new FrequencyElements(elements);
    }

    public static FrequencyElements of(Stream<Frequency> stream) {
        return new FrequencyElements(stream);
    }
}
