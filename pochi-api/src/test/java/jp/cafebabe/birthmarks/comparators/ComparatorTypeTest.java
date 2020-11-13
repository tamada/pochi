package jp.cafebabe.birthmarks.comparators;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

public class ComparatorTypeTest {

    @Test
    public void testType(){
        ComparatorType type = new ComparatorType("type1");

        assertThat(type, is(new ComparatorType("type1")));
        assertThat(type, is(Matchers.not(new ComparatorType("type2"))));
        assertThat(type, is(not(new Object())));

        assertThat(type.toString(), is("type1"));
    }

    @Test
    public void testHashCode() {
        ComparatorType type = new ComparatorType("type");
        
        assertThat(type.hashCode(), is(new ComparatorType("type").hashCode()));
        assertThat(type.hashCode(), is(not(new ComparatorType("type1").hashCode())));
        assertThat(type.hashCode(), is(not("type".hashCode())));
    }
}
