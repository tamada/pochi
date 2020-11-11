package jp.cafebabe.pochi.comparators.algorithms;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import jp.cafebabe.pochi.comparators.algorithms.Index2D;
import jp.cafebabe.pochi.comparators.algorithms.Size;
import org.junit.Test;

import java.util.Objects;
import java.util.Optional;

public class Index2DTest {
    @Test
    public void testBasic() {
        Index2D index = new Index2D(2, 3);

        assertThat(index.x(), is(2));
        assertThat(index.y(), is(3));
        assertThat(index.compute(Size.of(3, 5)), is(11));
    }

    @Test
    public void testRelativePoint() {
        Index2D index2D = new Index2D(2, 3);

        Optional<Index2D> relative1 = index2D.relativeOf(-2, 1);
        assertThat(relative1.isPresent(), is(true));
        assertThat(relative1.get(), is(new Index2D(0, 4)));

        Optional<Index2D> relative2 = index2D.relativeOf(2, -4);
        assertThat(relative2.isPresent(), is(false));

        Optional<Index2D> relative3 = index2D.relativeOf(-5, 1);
        assertThat(relative3.isPresent(), is(false));
    }

    @Test
    public void testEquals() {
        Index2D index2D = new Index2D(2, 3);

        assertThat(Objects.equals(index2D, new Object()), is(false));
        assertThat(Objects.equals(index2D, new Index2D(3, 2)), is(false));
        assertThat(Objects.equals(index2D, new Index2D(2, 4)), is(false));
        assertThat(Objects.equals(index2D, new Index2D(2, 3)), is(true));
    }
}
