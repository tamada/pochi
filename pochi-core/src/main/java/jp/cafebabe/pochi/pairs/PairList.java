package jp.cafebabe.pochi.pairs;

import java.util.List;
import java.util.Map;

public class PairList {
    private Map<String, List<String>> pairs;

    public PairList(Map<String, List<String>> pairs) {
        this.pairs = pairs;
    }

    public List<String> pairOf(String name) {
        return pairs.getOrDefault(name, List.of());
    }
}
