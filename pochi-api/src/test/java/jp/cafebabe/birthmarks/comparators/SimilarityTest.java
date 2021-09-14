package jp.cafebabe.birthmarks.comparators;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class SimilarityTest {
    @Test
    public void testSimilarityComparison() {
        var similarities = List.of(new Similarity(0.4),
                new Similarity(0.2), new Similarity(0.6),
                new Similarity(0.7), new Similarity(0.9),
                new Similarity(0.3), new Similarity(0.5))
                .stream().sorted()
                .collect(Collectors.toList());

        assertThat(similarities.size(), is(7));
        assertThat(similarities.get(0).value(), is(closeTo(0.2, 1E-8)));
        assertThat(similarities.get(1).value(), is(closeTo(0.3, 1E-8)));
        assertThat(similarities.get(2).value(), is(closeTo(0.4, 1E-8)));
        assertThat(similarities.get(3).value(), is(closeTo(0.5, 1E-8)));
        assertThat(similarities.get(4).value(), is(closeTo(0.6, 1E-8)));
        assertThat(similarities.get(5).value(), is(closeTo(0.7, 1E-8)));
        assertThat(similarities.get(6).value(), is(closeTo(0.9, 1E-8)));
    }

    @Test
    public void testSimilarityValue(){
        Similarity similarity = new Similarity(0.75);

        Threshold threshold1 = new Threshold(0.3);
        Threshold threshold2 = new Threshold(0.8);

        assertThat(similarity.isCloseTo(new Similarity(0.7501), 1E-2), is(true));
        assertThat(similarity.isCloseTo(new Similarity(0.8), 1E-2), is(false));
        assertThat(similarity.isStolen(threshold1), is(true));
        assertThat(similarity.isInconclusive(threshold2), is(true));

        assertThat(similarity.toString(), is("0.75"));
    }

    @Test
    public void testStolenCase1(){
        Similarity similarity = new Similarity(0.75);
        Threshold threshold = new Threshold(0.8);

        assertThat(similarity.isStolen(threshold), is(false));
    }

    @Test
    public void testStolenCase2(){
        Similarity similarity = new Similarity(0.9);
        Threshold threshold = new Threshold(0.8);

        assertThat(similarity.isStolen(threshold), is(true));
    }

    @Test
    public void testInconclusive(){
        Similarity similarity1 = new Similarity(0.4);
        Similarity similarity2 = new Similarity(0.2);
        Similarity similarity3 = new Similarity(0.8);

        Threshold threshold = new Threshold(0.75);

        assertThat(similarity1.isInconclusive(threshold), is(true));
        assertThat(similarity2.isInconclusive(threshold), is(false));
        assertThat(similarity3.isInconclusive(threshold), is(false));
    }
}
