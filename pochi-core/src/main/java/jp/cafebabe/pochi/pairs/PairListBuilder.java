package jp.cafebabe.pochi.pairs;

import io.vavr.control.Try;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.pochi.util.ResourceFindingHelper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class PairListBuilder implements Serializable {
    public static final String CONFIG_KEY = "pair.list";

    public static final PairList build(Configuration config) {
        return new PairListBuilder().buildImpl(ResourceFindingHelper.find(config.getProperty(CONFIG_KEY, null)));
    }

    private PairList buildImpl(Optional<URL> path) {
        return new PairList(readPairs(path));
    }

    private Map<String, String> readPairs(Optional<URL> path) {
        return path.map(p -> readPairs(p))
                .orElseGet(() -> new HashMap<>());
    }

    private Map<String, String> readPairs(URL url) {
        return readPairsImpl(url)
                .getOrElse(() -> new HashMap<>());
    }

    private Try<Map<String, String>> readPairsImpl(URL url) {
        return Try.withResources(() -> new BufferedReader(new InputStreamReader(url.openStream())))
                .of(in -> readPairsImpl(in));
    }

    private Map<String, String> readPairsImpl(BufferedReader in) {
        return in.lines()
                .filter(line -> !line.trim().startsWith("#"))
                .map(line -> line.split(","))
                .collect(Collectors.toMap(item -> item[0], item -> item[1], (before, after) -> before));
    }
}
