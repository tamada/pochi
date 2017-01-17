package com.github.ebis.birthmarks;

import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.ebis.birthmarks.entities.PairMakerType;
import com.github.ebis.birthmarks.pairs.DefaultRoundRobinPairMaker;
import com.github.ebis.birthmarks.pairs.PairMakers;
import com.github.ebis.birthmarks.pairs.RoundRobinPairMaker;
import com.github.ebis.birthmarks.pairs.SameNamePairMaker;

public class PairMakersTest {
    @Test
    public void testUC() throws Exception{
        PairMakers makers = new PairMakers();
        PairMakerType[] types = makers.availableTypes();
        assertThat(types.length, is(3));
        assertThat(types, is(arrayContainingInAnyOrder(new PairMakerType("RoundRobin"),
                new PairMakerType("RoundRobinWithSamePair"),
                new PairMakerType("SameName"))));

        PairMaker maker1 = makers.service(new PairMakerType("RoundRobin"));
        assertThat(maker1, is(instanceOf(PairMaker.class)));
        assertThat(maker1, is(instanceOf(RoundRobinPairMaker.class)));
        assertThat(maker1, is(instanceOf(DefaultRoundRobinPairMaker.class)));

        PairMaker maker2 = makers.service(new PairMakerType("SameName"));
        assertThat(maker2, is(instanceOf(PairMaker.class)));
        assertThat(maker2, is(instanceOf(SameNamePairMaker.class)));        

        assertThat(makers.available(new PairMakerType("RoundRobin")), is(true));
        assertThat(makers.available(new PairMakerType("RoundRobinWithSamePair")), is(true));
        assertThat(makers.available(new PairMakerType("SameName")), is(true));
        assertThat(makers.available(new PairMakerType("unknown")), is(false));
    }
}
