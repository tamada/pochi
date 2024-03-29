package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.Comparator;
import jp.cafebabe.birthmarks.comparators.Similarity;
import jp.cafebabe.birthmarks.comparators.Threshold;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.config.ConfigurationBuilder;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

public class CosineSimilarityComparatorTest extends BirthmarkBuilderHelper{
    private Comparator comparator;

    @Before
    public void buildComparator(){
        Configuration config = new ConfigurationBuilder().configuration();
        comparator = new CosineComparator(config);
    }

    @Test
    public void testSimilarity() throws Exception{
        Birthmark<String> birthmark1 = buildBirthmark("a", Stream.of("a", "b", "c", "d", "e"));
        Birthmark<String> birthmark2 = buildBirthmark("b", Stream.of("a", "b", "c", "d"));

        Similarity similarity = comparator.similarity(new Pair<>(birthmark1, birthmark2)).get();
        Threshold threshold = new Threshold(0.25);
        assertThat(similarity.value(), is(closeTo(0.894427, 1E-6)));
        assertThat(similarity.isCloseTo(new Similarity(0.894427), 1E-6), is(true));
        assertThat(similarity.isStolen(threshold), is(true));
        assertThat(similarity.isInconclusive(threshold), is(false));
        assertThat(similarity.isInnocent(threshold), is(false));
    }

}
