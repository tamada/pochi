package com.github.pochi.runner.birthmarks;

import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.junit.Test;

import com.github.pochi.runner.birthmarks.entities.BirthmarkType;

public class BirthmarkExtractorsTest {
    @Test
    public void testUC() throws Exception{
        BirthmarkSystem system = new BirthmarkSystem();
        BirthmarkType[] types = system.availableExtractors();
        assertThat(types.length, is(6));
        assertThat(types, is(arrayContainingInAnyOrder(
                Stream.of("uc", "2-gram", "3-gram", "4-gram", "5-gram", "6-gram")
                .map(BirthmarkType::new)
                .toArray(BirthmarkType[]::new))));

        BirthmarkExtractor extractor1 = system.extractor(new BirthmarkType("uc"));
        assertThat(extractor1, is(instanceOf(BirthmarkExtractor.class)));

        BirthmarkExtractor extractor2 = system.extractor(new BirthmarkType("4-gram"));
        assertThat(extractor2, is(instanceOf(BirthmarkExtractor.class)));
    }

    @Test
    public void testByExtractors() throws Exception{
        BirthmarkExtractors extractors = new BirthmarkExtractors();

        assertThat(extractors.available(new BirthmarkType("uc")), is(true));
        assertThat(extractors.available(new BirthmarkType("unknown")), is(false));
    }
}
