package com.github.pochi.runner.birthmarks;

import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.pochi.runner.birthmarks.BirthmarkExtractor;
import com.github.pochi.runner.birthmarks.BirthmarkExtractors;
import com.github.pochi.runner.birthmarks.BirthmarkSystem;
import com.github.pochi.runner.birthmarks.entities.BirthmarkType;

public class BirthmarkExtractorsTest {
    @Test
    public void testUC() throws Exception{
        BirthmarkSystem system = new BirthmarkSystem();
        BirthmarkType[] types = system.availableExtractors();
        assertThat(types.length, is(1));
        assertThat(types, is(arrayContainingInAnyOrder(new BirthmarkType("uc"))));


        BirthmarkExtractor extractor = system.extractor(new BirthmarkType("uc"));
        assertThat(extractor, is(instanceOf(BirthmarkExtractor.class)));
    }

    @Test
    public void testByExtractors() throws Exception{
        BirthmarkExtractors extractors = new BirthmarkExtractors();

        assertThat(extractors.available(new BirthmarkType("uc")), is(true));
        assertThat(extractors.available(new BirthmarkType("unknown")), is(false));
    }
}
