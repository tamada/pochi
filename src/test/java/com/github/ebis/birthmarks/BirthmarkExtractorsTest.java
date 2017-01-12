package com.github.ebis.birthmarks;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.github.ebis.birthmarks.entities.BirthmarkType;

public class BirthmarkExtractorsTest {
    private BirthmarkExtractors extractors;

    @Before
    public void setUp(){
        extractors = new BirthmarkExtractors();
    }

    @Test
    public void testUC() throws Exception{
        BirthmarkType[] types = extractors.availableTypes();
        assertThat(types.length, is(1));
        assertThat(types[0], is(new BirthmarkType("uc")));

        BirthmarkExtractor extractor = extractors.extractor(new BirthmarkType("uc"));
        assertThat(extractor, is(instanceOf(BirthmarkExtractor.class)));
    }
}
