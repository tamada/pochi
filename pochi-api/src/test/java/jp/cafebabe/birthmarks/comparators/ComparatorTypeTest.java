package jp.cafebabe.birthmarks.comparators;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class ComparatorTypeTest {

    @Test
    public void testType(){
        ComparatorType type = ComparatorType.of("type1");

        assertThat(type, is(ComparatorType.of("type1")));
        assertThat(type, is(not(ComparatorType.of("type2"))));
        assertThat(type, is(not(new Object())));

        assertThat(type.toString(), is("type1"));
    }

    @Test
    public void testHashCode() {
        ComparatorType type = ComparatorType.of("type");
        
        assertThat(type.hashCode(), is(ComparatorType.of("type").hashCode()));
        assertThat(type.hashCode(), is(not(ComparatorType.of("type1").hashCode())));
        assertThat(type.hashCode(), is(not("type".hashCode())));
    }
}
