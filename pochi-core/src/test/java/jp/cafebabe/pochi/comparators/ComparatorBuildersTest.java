package jp.cafebabe.pochi.comparators;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import jp.cafebabe.birthmarks.comparators.ComparatorType;

import java.util.List;
import java.util.stream.Collectors;

public class ComparatorBuildersTest {
    @Test
    public void testBasic() {
        List<ComparatorType> types = new ComparatorBuilders().availableTypes()
                .collect(Collectors.toList());

        assertThat(types, hasItem(new ComparatorType("JaccardIndex")));
        assertThat(types, hasItem(new ComparatorType("DiceIndex")));
        assertThat(types, hasItem(new ComparatorType("EditDistance")));
        assertThat(types, hasItem(new ComparatorType("SimpsonIndex")));
    }
}
