package jp.cafebabe.pochi.nasubi;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CoupleTest {
    @Test
    public void testBasic() {
        Couple<Integer, String> couplet = new Couple<>(100, "one hundred");

        assertThat(couplet.map((left, right) -> left + right), is("100one hundred"));
        assertThat(couplet.filter((left, right) -> left == 100), is(true));
        assertThat(couplet.filter((left, right) -> left > 100), is(false));
        assertThat(couplet.map(left -> left * 200, right -> right + right), is(new Couple<>(20000, "one hundredone hundred")));
    }

    @Test
    public void testApply() {
        List<String> list = new ArrayList<>();

        Couple<String, String> couplet = new Couple<>("100", "200");
        couplet.apply((left, right) -> { list.add(left); list.add(right); });

        assertThat(list.size(), is(2));
        assertThat(list.get(0), is("100"));
        assertThat(list.get(1), is("200"));
    }

    @Test
    public void testMatched() {
        Couple<Integer, String> couplet = new Couple<>(100, "one hundred");

        assertThat(couplet, is(new Couple<>(100, "one hundred")));
        assertThat(couplet, is(not(new Couple<>(100, "two hundred"))));
        assertThat(couplet, is(not(new Couple<>(200, "one hundred"))));
        assertThat(couplet, is(not(new Couple<>("one hundred", 100))));
        assertThat(couplet, is(not(new Object())));
        assertThat(couplet.hashCode(), is(not(new Couple<>("one hundred", 100).hashCode())));
    }

    @Test(expected = NullPointerException.class)
    public void testNullValue1() throws Exception {
        new Couple<Integer, String>(null, "hoge");
    }

    @Test(expected = NullPointerException.class)
    public void testNullValue2() throws Exception {
        new Couple<Integer, String>(100, null);
    }
}
