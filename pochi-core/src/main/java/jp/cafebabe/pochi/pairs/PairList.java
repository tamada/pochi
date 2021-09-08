package jp.cafebabe.pochi.pairs;

import java.util.Map;
import java.util.Optional;

public class PairList {
    private Map<String, String> pairs;

    public PairList(Map<String, String> pairs) {
        this.pairs = pairs;
    }

    public Optional<String> pairOf(String name) {
        return Optional.ofNullable(pairs.get(name));
    }
}
