package jp.cafebabe.pochi.runner.util;

import static jp.cafebabe.pochi.runner.Assert.assertThrows;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class VectorMapTest {
    private VectorMap<String, Integer> map;

    @Before
    public void setUp(){
        map = new VectorMap<>();
        map.put("primes", 2);
        map.put("primes", 3);
        map.put("primes", 5);
        map.put("primes", 7);

        map.put("odd", 1);
        map.put("odd", 3);
        map.put("odd", 5);
        map.put("odd", 7);
        map.put("odd", 9);

        map.put("even", 2);
        map.put("even", 4);
        map.put("even", 6);
        map.put("even", 8);
    }

    @Test
    public void testTotalSize(){
        assertThat(map.size(), is(3));
    }

    @Test
    public void testEachSize(){
        assertThat(map.size("primes"), is(4));
        assertThat(map.size("odd"),    is(5));
        assertThat(map.size("even"),   is(4));
        assertThat(map.size("nolist"), is(-1));
    }

    @Test
    public void testElement(){
        assertThat(map.get("primes", 0), is(2));
        assertThat(map.get("primes", 1), is(3));
        assertThat(map.get("even", 1), is(4));
    }

    @Test
    public void testThrows(){
        assertThrows(NoSuchElementException.class, () -> map.get("nolist", 0));
        assertThrows(IndexOutOfBoundsException.class, () -> map.get("primes", 5));
    }

    @Test
    public void testRemoveVector(){
        map.remove("odd");
        assertThat(map.get("odd"), is(nullValue()));
        assertThat(map.get("primes"),
                is(contains(new Integer(2), new Integer(3), new Integer(5), new Integer(7))));

        assertThat(map.get("even"),
                is(contains(new Integer(2), new Integer(4), new Integer(6), new Integer(8))));
    }

    @Test
    public void testStream(){
        Stream<VectorMap.Entry<String, List<Integer>>> stream = map.stream();
        List<VectorMap.Entry<String, List<Integer>>> list = stream.collect(Collectors.toList());
        assertThat(list.size(), is(3));
        assertThat(list.get(0).key(), is("primes"));
        assertThat(list.get(0).value(), is(contains(new Integer(2), new Integer(3), new Integer(5), new Integer(7))));
        assertThat(list.get(1).key(), is("even"));
        assertThat(list.get(1).value(), is(contains(new Integer(2), new Integer(4), new Integer(6), new Integer(8))));
        assertThat(list.get(2).key(), is("odd"));
        assertThat(list.get(2).value(), is(contains(new Integer(1), new Integer(3), new Integer(5), new Integer(7), new Integer(9))));
    }

    @Test
    public void testFlatStream(){
        Stream<VectorMap.Entry<String, Integer>> stream = map.flatStream();
        List<VectorMap.Entry<String, Integer>> list = stream.collect(Collectors.toList());
        assertThat(list.size(), is(13));
        assertThat(list.get(0).key(), is("primes"));
        assertThat(list.get(0).value(), is(2));
        assertThat(list.get(1).key(), is("primes"));
        assertThat(list.get(1).value(), is(3));
        assertThat(list.get(2).key(), is("primes"));
        assertThat(list.get(2).value(), is(5));
        assertThat(list.get(3).key(), is("primes"));
        assertThat(list.get(3).value(), is(7));

        assertThat(list.get(4).key(), is("even"));
        assertThat(list.get(4).value(), is(2));
        assertThat(list.get(5).key(), is("even"));
        assertThat(list.get(5).value(), is(4));
        assertThat(list.get(6).key(), is("even"));
        assertThat(list.get(6).value(), is(6));
        assertThat(list.get(7).key(), is("even"));
        assertThat(list.get(7).value(), is(8));

        assertThat(list.get(8).key(), is("odd"));
        assertThat(list.get(8).value(), is(1));
        assertThat(list.get(9).key(), is("odd"));
        assertThat(list.get(9).value(), is(3));
        assertThat(list.get(10).key(), is("odd"));
        assertThat(list.get(10).value(), is(5));
        assertThat(list.get(11).key(), is("odd"));
        assertThat(list.get(11).value(), is(7));
        assertThat(list.get(12).key(), is("odd"));
        assertThat(list.get(12).value(), is(9));
    }

    @Test
    public void testRemoveElement(){
        map.remove("primes", 1);

        assertThat(map.get("primes"),
                is(contains(new Integer(2), new Integer(5), new Integer(7))));

        assertThat(map.get("even"),
                is(contains(new Integer(2), new Integer(4), new Integer(6), new Integer(8))));

        assertThat(map.get("odd"),
                is(contains(new Integer(1), new Integer(3), new Integer(5), new Integer(7), new Integer(9))));
    }

    @Test
    public void testEachElement(){
        assertThat(map.get("primes"),
                is(contains(new Integer(2), new Integer(3), new Integer(5), new Integer(7))));

        assertThat(map.get("even"),
                is(contains(new Integer(2), new Integer(4), new Integer(6), new Integer(8))));

        assertThat(map.get("odd"),
                is(contains(new Integer(1), new Integer(3), new Integer(5), new Integer(7), new Integer(9))));
    }
}
