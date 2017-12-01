package com.github.pochi.izumo;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.Test;

public class ServiceLoaderTest {
    @Test
    @SuppressWarnings("rawtypes")
    public void testBasic() {
        ServiceLoader<PairMatcher> loader = ServiceLoader.load(PairMatcher.class);
        List<PairMatcher> list = StreamSupport.stream(loader.spliterator(), false)
                .collect(Collectors.toList());

        assertThat(list.size(), is(2));
        assertThat(list.get(0).type(), is(new PairMatcherType("RoundRobin")));
        assertThat(list.get(1).type(), is(new PairMatcherType("RoundRobinWithSamePair")));
    }
}
