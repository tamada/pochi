package jp.cafebabe.pochi.pairs;

import io.vavr.control.Try;
import jp.cafebabe.birthmarks.config.Configuration;

import java.io.BufferedReader;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class PairListBuilder implements Serializable {
    public static final String CONFIG_KEY = "pair.list";

    public static final PairList build(Configuration config) {
        return new PairListBuilder().buildImpl(
                Optional.ofNullable(config.getProperty(CONFIG_KEY, null))
                        .map(p -> Path.of(p)));
    }

    private PairList buildImpl(Optional<Path> path) {
        return new PairList(readPairs(path));
    }

    private Map<String, String> readPairs(Optional<Path> path) {
        return path.map(p -> readPairs(p))
                .orElseGet(() -> new HashMap<>());
    }

    private Map<String, String> readPairs(Path path) {
        return readPairsImpl(path)
                .getOrElse(() -> new HashMap<>());
    }

    private Try<Map<String, String>> readPairsImpl(Path path) {
        return Try.withResources(() -> Files.newBufferedReader(path))
                .of(in -> readPairsImpl(in));
    }

    private Map<String, String> readPairsImpl(BufferedReader in) {
        return in.lines()
                .filter(line -> !line.trim().startsWith("#"))
                .map(line -> line.split(","))
                .collect(Collectors.toMap(item -> item[0], item -> item[1], (before, after) -> before));
    }
}
