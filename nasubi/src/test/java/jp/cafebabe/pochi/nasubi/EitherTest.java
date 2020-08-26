package jp.cafebabe.pochi.nasubi;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.stream.Stream;

public class EitherTest {
    @Test
    public void testBasicLeft() {
        Either<String, Integer> left = Either.left("left");

        assertThat(left.isLeft(), is(true));
        assertThat(left.isRight(), is(false));
        assertThat(left.rightStream().count(), is(0L));
        assertThat(left.leftStream().count(), is(1L));
        assertThat(left.left().isPresent(), is(true));
        assertThat(left.right().isPresent(), is(false));
    }
}
