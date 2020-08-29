package jp.cafebabe.pochi.birthmarks;

import io.vavr.control.Try;
import jp.cafebabe.pochi.birthmarks.comparators.Comparator;
import jp.cafebabe.pochi.birthmarks.comparators.ComparatorBuilder;
import jp.cafebabe.pochi.birthmarks.comparators.ComparatorType;
import jp.cafebabe.pochi.birthmarks.config.Configuration;
import jp.cafebabe.pochi.birthmarks.config.ConfigurationBuilder;
import jp.cafebabe.pochi.birthmarks.entities.BirthmarkType;
import jp.cafebabe.pochi.birthmarks.extractors.Extractor;
import jp.cafebabe.pochi.birthmarks.extractors.ExtractorBuilder;
import jp.cafebabe.pochi.birthmarks.pairs.PairMatcher;
import jp.cafebabe.pochi.birthmarks.pairs.PairMatcherBuilder;
import jp.cafebabe.pochi.birthmarks.pairs.PairMatcherBuilders;
import jp.cafebabe.pochi.birthmarks.pairs.PairMatcherType;
import jp.cafebabe.pochi.birthmarks.parsers.DefaultParser;
import jp.cafebabe.pochi.kunai.entries.KunaiException;
import jp.cafebabe.pochi.kunai.source.DataSource;
import jp.cafebabe.pochi.kunai.source.factories.DefaultDataSourceFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class BirthmarkSystemHelper {
    private BirthmarkSystem system = new BirthmarkSystem();
    private Configuration config;
    private PairMatcherBuilders pairMatchers = new PairMatcherBuilders();

    public BirthmarkSystemHelper() {
        this.config = loadConfig();
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }

    public Configuration config() {
        return config;
    }

    public DataSource source(String path) throws KunaiException {
        return new DefaultDataSourceFactory()
                .build(Paths.get(path));
    }

    public Comparator comparator(String name) {
        return system.comparator(new ComparatorType(name))
                .build(config);
    }

    public Extractor extractor(String name) {
        return system.extractor(new BirthmarkType(name))
                .build(config);
    }

    public PairMatcher matcher(String name) {
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

    private static Configuration loadConfig() {
        Optional<String> value = Optional.ofNullable(System.getenv("POCHI_CONFIG_PATH"));
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

    public void register(PairMatcherBuilder builder) {
        pairMatchers.register(builder);
    }
}
