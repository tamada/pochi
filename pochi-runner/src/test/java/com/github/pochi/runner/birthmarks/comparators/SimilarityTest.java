package com.github.pochi.runner.birthmarks.comparators;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SimilarityTest {

    @Test
    public void testSimilarityValue(){
        Similarity similarity = new Similarity(0.75);

        Threshold threshold1 = new Threshold(0.3);
        Threshold threshold2 = new Threshold(0.2);

        assertThat(similarity.isCloseTo(new Similarity(0.7501), 1E-2), is(true));
        assertThat(similarity.isCloseTo(new Similarity(0.8), 1E-2), is(false));
        assertThat(similarity.isStolen(threshold1), is(true));
        assertThat(similarity.isInconclusive(threshold2), is(true));

        assertThat(similarity.toString(), is("0.75"));
    }

    @Test
    public void testStolen(){
        Similarity similarity = new Similarity(0.25);

        Threshold threshold1 = new Threshold(0.3);
        Threshold threshold2 = new Threshold(0.2);

        assertThat(similarity.isStolen(threshold1), is(false));
        assertThat(similarity.isInnocent(threshold1), is(true));
        assertThat(similarity.isInconclusive(threshold2), is(true));
    }
}
