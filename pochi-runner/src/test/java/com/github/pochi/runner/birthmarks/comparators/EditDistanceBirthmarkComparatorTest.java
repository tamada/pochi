package com.github.pochi.runner.birthmarks.comparators;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.github.pochi.birthmarks.comparators.Comparator;
import com.github.pochi.birthmarks.comparators.Similarity;
import com.github.pochi.birthmarks.comparators.Threshold;
import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.config.ConfigurationBuilder;
import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.pairs.Pair;

public class EditDistanceBirthmarkComparatorTest extends BirthmarkBuilderHelper{
    private Comparator comparator;
    private Configuration config;

    @Before
    public void buildComparator(){
        config = new ConfigurationBuilder().configuration();
        comparator = new EditDistanceComparator(config);
    }

    @Test
    public void testSimilarity() throws Exception{
        Birthmark birthmark1 = buildBirthmark("a", Stream.of("a", "b", "c", "d", "e"));
        Birthmark birthmark2 = buildBirthmark("b", Stream.of("a", "b", "c", "d"));

        Similarity similarity = comparator.similarity(new Pair<>(birthmark1, birthmark2), (pair, exception) -> {}).get();
        Threshold threshold = new Threshold(0.25);
        assertThat(similarity.isCloseTo(new Similarity(1 - (1d / 5)), 1E-6), is(true));
        assertThat(similarity.isStolen(threshold), is(true));
        assertThat(similarity.isInconclusive(threshold), is(false));
        assertThat(similarity.isInnocent(threshold), is(false));
    }

}
