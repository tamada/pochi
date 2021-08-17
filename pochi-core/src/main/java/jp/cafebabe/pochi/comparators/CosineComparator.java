package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.*;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Elements;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CosineComparator extends AbstractComparator {
    private static final ComparatorType thisType = ComparatorType.of("Cosine");

    public static final class Builder implements ComparatorBuilder {
        @Override
        public ComparatorType type() {
            return thisType;
        }

        @Override
        public Comparator build(Configuration config) {
            return new CosineComparator(config);
        }
    }

    public CosineComparator(Configuration config) {
        super(thisType, config);
    }

    protected <T> Similarity calculate(Birthmark<T> left, Birthmark<T> right) {
        return calculateImpl(toFreqMap(left.elements()), toFreqMap(right.elements()));
    }

    private <T> Map<String, Integer> toFreqMap(Elements<T> elements) {
        return elements.asFrequencies().stream()
                .collect(Collectors.toMap(f -> f.left(), f -> f.right()));
    }

    private Similarity calculateImpl(Map<String, Integer> left, Map<String, Integer> right) {
        double leftNorm = norm(left);
        double rightNorm = norm(right);
        double innerProduct = innerProduct(left, right);
        return new Similarity(innerProduct / (leftNorm * rightNorm));
    }

    private double innerProduct(Map<String, Integer> left, Map<String, Integer> right) {
        Stream<String> keyStream = keyStream(left, right);
        return keyStream.map(key -> product(key, left, right))
                .reduce(0, (b, c) -> b + c);
    }

    private int product(String key, Map<String, Integer> left, Map<String, Integer> right) {
        return left.getOrDefault(key, 0) *
                right.getOrDefault(key, 0);
    }

    private Stream<String> keyStream(Map<String, Integer> left, Map<String, Integer> right) {
        return Stream.concat(left.keySet().stream(), right.keySet().stream())
                .distinct();
    }

    private double norm(Map<String, Integer> map) {
        return Math.sqrt(map.values()
                .stream().map(v -> v * v)
                .reduce(0, (b, c) -> b + c));

    }
}
