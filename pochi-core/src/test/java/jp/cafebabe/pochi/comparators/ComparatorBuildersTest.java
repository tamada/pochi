package jp.cafebabe.pochi.comparators;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import jp.cafebabe.birthmarks.comparators.ComparatorType;

import java.util.List;
import java.util.stream.Collectors;

public class ComparatorBuildersTest {
    @Test
    public void testBasic() {
        List<ComparatorType> types = new ComparatorBuilders().availableTypes()
                .collect(Collectors.toList());

        assertThat(types, hasItem(ComparatorType.of("Cosine")));
        assertThat(types, hasItem(ComparatorType.of("JaccardIndex")));
        assertThat(types, hasItem(ComparatorType.of("DiceIndex")));
        assertThat(types, hasItem(ComparatorType.of("EditDistance")));
        assertThat(types, hasItem(ComparatorType.of("SimpsonIndex")));
    }
}
