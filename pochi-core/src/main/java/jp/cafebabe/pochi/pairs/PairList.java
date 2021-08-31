package jp.cafebabe.pochi.pairs;

import java.util.Map;
import java.util.Optional;

public class PairList {
    private Map<String, String> pairs;

    public PairList(Map<String, String> pairs) {
        this.pairs = pairs;
    }

    private Optional<String> pair(String name) {
        return Optional.ofNullable(pairs.get(name));
    }
}
