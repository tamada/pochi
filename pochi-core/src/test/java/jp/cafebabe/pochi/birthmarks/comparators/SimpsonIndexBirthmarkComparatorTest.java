package jp.cafebabe.pochi.birthmarks.comparators;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import jp.cafebabe.pochi.birthmarks.config.Configuration;
import jp.cafebabe.pochi.birthmarks.config.ConfigurationBuilder;
import jp.cafebabe.pochi.birthmarks.entities.Birthmark;
import jp.cafebabe.pochi.birthmarks.pairs.Pair;

public class SimpsonIndexBirthmarkComparatorTest extends BirthmarkBuilderHelper{
    private Comparator comparator;
    private Configuration config;

    @Before
    public void buildComparator(){
        config = new ConfigurationBuilder().configuration();
        comparator = new SimpsonIndexComparator(config);
    }

    @Test
    public void testSimilarity() throws Exception{
        Birthmark birthmark1 = buildBirthmark("a", Stream.of("a", "b", "c", "d", "e"));
        Birthmark birthmark2 = buildBirthmark("b", Stream.of("a", "b", "c", "d"));

        Similarity similarity = comparator.similarity(new Pair<>(birthmark1, birthmark2)).get();
        Threshold threshold = new Threshold(0.25);
        assertThat(similarity.isCloseTo(new Similarity(4d / 4), 1E-6), is(true));
        assertThat(similarity.isStolen(threshold), is(true));
        assertThat(similarity.isInconclusive(threshold), is(false));
        assertThat(similarity.isInnocent(threshold), is(false));
    }

    @Test
    public void testComparison() throws Exception{
        Birthmark birthmark1 = buildBirthmark("a", Stream.of("a", "b", "c", "d", "e"));
        Birthmark birthmark2 = buildBirthmark("b", Stream.of("a", "b", "c", "d"));

        Comparison comparison = comparator.compare(birthmark1, birthmark2).get();
        assertThat(comparison.left(), is(birthmark1));
        assertThat(comparison.right(), is(birthmark2));

        Threshold threshold = new Threshold(0.25);
        assertThat(comparison.isStolen(threshold), is(true));
        assertThat(comparison.isInconclusive(threshold), is(false));
        assertThat(comparison.isInnocent(threshold), is(false));

        assertThat(comparison.toString(), is("a,b,1.0"));
    }
}
