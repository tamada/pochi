package jp.cafebabe.pochi;

import jp.cafebabe.birthmarks.BirthmarkParser;
import jp.cafebabe.birthmarks.comparators.Comparator;
import jp.cafebabe.birthmarks.comparators.ComparatorBuilder;
import jp.cafebabe.birthmarks.comparators.ComparatorType;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.config.ConfigurationBuilder;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.extractors.Extractor;
import jp.cafebabe.birthmarks.extractors.ExtractorBuilder;
import jp.cafebabe.birthmarks.pairs.PairMatcher;
import jp.cafebabe.birthmarks.pairs.PairMatcherBuilder;
import jp.cafebabe.birthmarks.pairs.PairMatcherType;
import jp.cafebabe.kunai.entries.KunaiException;
import jp.cafebabe.kunai.source.DataSource;
import jp.cafebabe.kunai.source.factories.DataSourceFactory;
import jp.cafebabe.pochi.pairs.PairMatcherBuilders;
import jp.cafebabe.pochi.parsers.DefaultParser;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class BirthmarkSystemHelper {
    private BirthmarkSystem system = new BirthmarkSystem();
    private Configuration config;
    private PairMatcherBuilders pairMatchers = new PairMatcherBuilders();

    public BirthmarkSystemHelper() {
        this(Map.of());
    }

    public BirthmarkSystemHelper(Map<String, String> env) {
        Environment envs = new Environment(env);
        this.config = loadConfig(envs);
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }

    public Configuration config() {
        return config;
    }

    public DataSource source(String path) throws KunaiException {
        return DataSourceFactory.instance()
                .build(Paths.get(path));
    }

    public Comparator comparator(String name) {
        return system.comparator(ComparatorType.of(name))
                .build(config);
    }

    public Extractor extractor(String name) {
        return system.extractor(BirthmarkType.of(name))
                .build(config);
    }

    public PairMatcher<Birthmark<?>> matcher(String name) {
        return pairMatchers.builder(new PairMatcherType(name))
                .build(config);
    }

    public BirthmarkParser parser() {
        return new DefaultParser(config);
    }

    public String[] comparatorNames() {
        return toStringArray(system.availableComparators());
    }

    public String[] extractorNames() {
        return toStringArray(system.availableExtractors());
    }

    public String[] matcherNames() {
        return toStringArray(pairMatchers.availableTypes());
    }

    private static <T> String[] toStringArray(T[] array) {
        return toStringArray(Arrays.stream(array));
    }

    private static <T> String[] toStringArray(Stream<T> stream) {
        return stream
                .map(object -> object.toString())
                .toArray(size -> new String[size]);
    }

    private static Configuration loadConfig(Environment envs) {
        Optional<String> value = Optional.ofNullable(envs.getenv("POCHI_CONFIG_PATH"));
        return value.map(BirthmarkSystemHelper::toURL)
                .map(url -> new ConfigurationBuilder(url))
                .orElse(new ConfigurationBuilder())
                .configuration();
    }

    private static Optional<URL> toURL(String path) {
        try {
            return Optional.of(Paths.get(path)
                    .toUri().toURL());
        } catch (MalformedURLException e) {
            return Optional.empty();
        }
    }

    public void register(ExtractorBuilder builder) {
        system.register(builder);
    }

    public void register(ComparatorBuilder builder) {
        system.register(builder);
    }

    public void register(PairMatcherBuilder<Birthmark<?>> builder) {
        pairMatchers.register(builder);
    }
}
