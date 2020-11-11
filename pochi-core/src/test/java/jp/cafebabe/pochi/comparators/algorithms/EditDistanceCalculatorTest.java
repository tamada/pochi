package jp.cafebabe.pochi.comparators.algorithms;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import jp.cafebabe.pochi.comparators.algorithms.EditDistanceCalculator;
import org.junit.Test;

import java.util.List;

public class EditDistanceCalculatorTest {
    private EditDistanceCalculator<String> calculator = new EditDistanceCalculator<>();

    @Test
    public void testBasic() {
        int distance = calculator.compute(List.of("a", "b", "c"), List.of("b", "c", "d", "e"));
        assertThat(distance, is(3));
    }
}
