package jp.cafebabe.pochi.nasubi;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CoupletTest {
    @Test
    public void testBasic() {
        Couplet<Integer, String> couplet = new Couplet<>(100, "one hundred");

        assertThat(couplet.map((left, right) -> left + right), is("100one hundred"));
        assertThat(couplet.filter((left, right) -> left == 100), is(true));
        assertThat(couplet.filter((left, right) -> left > 100), is(false));
        assertThat(couplet.map(left -> left * 200, right -> right + right), is(new Couplet<>(20000, "one hundredone hundred")));
    }

    @Test
    public void testApply() {
        List<String> list = new ArrayList<>();

        Couplet<String, String> couplet = new Couplet<>("100", "200");
        couplet.apply((left, right) -> { list.add(left); list.add(right); });

        assertThat(list.size(), is(2));
        assertThat(list.get(0), is("100"));
        assertThat(list.get(1), is("200"));
    }

    @Test
    public void testMatched() {
        Couplet<Integer, String> couplet = new Couplet<>(100, "one hundred");

        assertThat(couplet, is(new Couplet<>(100, "one hundred")));
        assertThat(couplet, is(not(new Couplet<>(100, "two hundred"))));
        assertThat(couplet, is(not(new Couplet<>(200, "one hundred"))));
        assertThat(couplet, is(not(new Couplet<>("one hundred", 100))));
        assertThat(couplet, is(not(new Object())));
        assertThat(couplet.hashCode(), is(not(new Couplet<>("one hundred", 100).hashCode())));
    }

    @Test(expected = NullPointerException.class)
    public void testNullValue1() throws Exception {
        new Couplet<Integer, String>(null, "hoge");
    }

    @Test(expected = NullPointerException.class)
    public void testNullValue2() throws Exception {
        new Couplet<Integer, String>(100, null);
    }
}
