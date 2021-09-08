package jp.cafebabe.pochi.pairs;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.Test;

import jp.cafebabe.birthmarks.pairs.PairMatcherBuilder;
import jp.cafebabe.birthmarks.pairs.PairMatcherType;

public class ServiceLoaderTest {
    @SuppressWarnings("rawtypes")
    @Test
    public void testBasic() {
        ServiceLoader<PairMatcherBuilder> loader = ServiceLoader.load(PairMatcherBuilder.class);
        List<PairMatcherBuilder> list = StreamSupport.stream(loader.spliterator(), false)
                .sorted((builder1, builder2) -> builder1.type().toString().compareTo(builder2.type().toString()))
                .collect(Collectors.toList());

        assertThat(list.size(), is(4));
        assertThat(list.get(0).type(), is(new PairMatcherType("Guessed")));
        assertThat(list.get(1).type(), is(new PairMatcherType("RoundRobin")));
        assertThat(list.get(2).type(), is(new PairMatcherType("RoundRobinWithSamePair")));
        assertThat(list.get(3).type(), is(new PairMatcherType("Specified")));
    }
}
