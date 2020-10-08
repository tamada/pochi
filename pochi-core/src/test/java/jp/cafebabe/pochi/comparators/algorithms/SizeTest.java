package jp.cafebabe.pochi.comparators.algorithms;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import jp.cafebabe.pochi.comparators.algorithms.Index2D;
import jp.cafebabe.pochi.comparators.algorithms.Size;
import org.junit.Test;

public class SizeTest {
    @Test
    public void testBasic() {
        Index2D[] indexes = Size.of(3, 4).stream().toArray(size -> new Index2D[size]);

        assertThat(indexes.length, is(12));
        assertThat(indexes[ 0], is(new Index2D(0, 0)));
        assertThat(indexes[ 1], is(new Index2D(0, 1)));
        assertThat(indexes[ 2], is(new Index2D(0, 2)));
        assertThat(indexes[ 3], is(new Index2D(0, 3)));
        assertThat(indexes[ 4], is(new Index2D(1, 0)));
        assertThat(indexes[ 5], is(new Index2D(1, 1)));
        assertThat(indexes[ 6], is(new Index2D(1, 2)));
        assertThat(indexes[ 7], is(new Index2D(1, 3)));
        assertThat(indexes[ 8], is(new Index2D(2, 0)));
        assertThat(indexes[ 9], is(new Index2D(2, 1)));
        assertThat(indexes[10], is(new Index2D(2, 2)));
        assertThat(indexes[11], is(new Index2D(2, 3)));
    }
}
