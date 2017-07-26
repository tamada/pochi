package com.github.pochi.runner.birthmarks.comparators;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.github.pochi.runner.birthmarks.BirthmarkComparator;
import com.github.pochi.runner.birthmarks.entities.Birthmark;
import com.github.pochi.runner.birthmarks.entities.Pair;
import com.github.pochi.runner.config.Configuration;
import com.github.pochi.runner.config.ConfigurationBuilder;

public class SimpsonIndexBirthmarkComparatorTest extends BirthmarkBuilderHelper{
    private BirthmarkComparator comparator;
    private Configuration config;

    @Before
    public void buildComparator(){
        comparator = new SimpsonIndexBirthmarkComparator();
        config = new ConfigurationBuilder().configuration();
    }

    @Test
    public void testSimilarity() throws Exception{
        Birthmark birthmark1 = buildBirthmark("a", Stream.of("a", "b", "c", "d", "e"));
        Birthmark birthmark2 = buildBirthmark("b", Stream.of("a", "b", "c", "d"));

        Similarity similarity = comparator.similarity(new Pair<>(birthmark1, birthmark2), config);
        Threshold threshold = new Threshold(0.25);
        assertThat(similarity.value, is(closeTo(4d / 4, 1E-6)));
        assertThat(similarity.isCloseTo(new Similarity(4d / 4), 1E-6), is(true));
        assertThat(similarity.isStolen(threshold), is(true));
        assertThat(similarity.isInconclusive(threshold), is(false));
        assertThat(similarity.isInnocent(threshold), is(false));
    }

    @Test
    public void testComparison() throws Exception{
        Birthmark birthmark1 = buildBirthmark("a", Stream.of("a", "b", "c", "d", "e"));
        Birthmark birthmark2 = buildBirthmark("b", Stream.of("a", "b", "c", "d"));

        Comparison comparison = comparator.compare(birthmark1, birthmark2, config);
        assertThat(comparison.left(), is(birthmark1));
        assertThat(comparison.right(), is(birthmark2));

        Similarity similarity = comparison.similarity();
        Threshold threshold = new Threshold(0.25);
        assertThat(similarity.value, is(closeTo(4d / 4, 1E-6)));
        assertThat(comparison.isStolen(threshold), is(true));
        assertThat(comparison.isInconclusive(threshold), is(false));
        assertThat(comparison.isInnocent(threshold), is(false));

        assertThat(comparison.toString(), is("a,b,1.0"));
    }
}
