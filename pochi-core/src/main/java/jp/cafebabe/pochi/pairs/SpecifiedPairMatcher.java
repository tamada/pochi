package jp.cafebabe.pochi.pairs;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Pair;
import jp.cafebabe.birthmarks.pairs.AbstractPairMatcher;
import jp.cafebabe.birthmarks.pairs.PairMatcherType;
import jp.cafebabe.birthmarks.pairs.Streamable;

import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * This matcher matches the pair by the specified pairs from the csv file.
 * The csv file is given through the property of {@link Configuration <code>Configruation</code>} with key <code>pair.list</code>.
 *
 * <pre><code>
 * pochi.config().put("pair.list", "csv/file/path");
 * matcher = pochi.matcher("Specified");
 * ....
 * </code></pre>
 */
public class SpecifiedPairMatcher<T extends Serializable> extends AbstractPairMatcher<T> {
    public static final PairMatcherType TYPE = new PairMatcherType("Specified");

    private CorrespondenceChecker<T> checker;
    private PairList pairs;

    public SpecifiedPairMatcher(Configuration config, CorrespondenceChecker<T> checker) {
        super(TYPE, config);
        this.pairs = PairListBuilder.build(config);
        this.checker = checker;
    }

    @Override
    public Stream<Pair<T>> match(Streamable<T> birthmarks) {
        return birthmarks.stream()
                .flatMap(item -> makeMatch(item, birthmarks));
    }

    @Override
    public Stream<Pair<T>> match(Streamable<T> birthmarks1, Streamable<T> birthmarks2) {
        return birthmarks1.stream()
                .flatMap(item -> makeMatch(item, birthmarks2));
    }

    private Stream<Pair<T>> makeMatch(T item, Streamable<T> birthmarks) {
        Optional<String> opponent = pairs.pairOf(checker.extract(item));
        return opponent.map(baseItem -> birthmarks.stream()
                .filter(targetItem -> checker.isCorrespond(baseItem, targetItem))
                .map(item2 -> new Pair<>(item, item2)))
                .orElse(Stream.empty());
    }

    @Override
    public long count(Streamable<T> birthmarks) {
        return match(birthmarks).count();
    }

    @Override
    public long count(Streamable<T> birthmarks1, Streamable<T> birthmarks2) {
        return match(birthmarks1, birthmarks2).count();
    }
}
