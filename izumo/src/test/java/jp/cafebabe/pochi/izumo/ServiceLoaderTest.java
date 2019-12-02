package jp.cafebabe.pochi.izumo;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.Test;

import jp.cafebabe.pochi.birthmarks.pairs.PairMatcherBuilder;
import jp.cafebabe.pochi.birthmarks.pairs.PairMatcherType;

public class ServiceLoaderTest {
    @SuppressWarnings("rawtypes")
    @Test
    public void testBasic() {
        ServiceLoader<PairMatcherBuilder> loader = ServiceLoader.load(PairMatcherBuilder.class);
        List<PairMatcherBuilder> list = StreamSupport.stream(loader.spliterator(), false)
                .collect(Collectors.toList());

        assertThat(list.size(), is(3));
        assertThat(list.get(0).type(), is(new PairMatcherType("RoundRobin")));
        assertThat(list.get(1).type(), is(new PairMatcherType("RoundRobinWithSamePair")));
        assertThat(list.get(2).type(), is(new PairMatcherType("Guessed")));
    }
}
