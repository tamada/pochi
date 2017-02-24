package com.github.pochi.runner.util;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import static com.github.pochi.runner.Assert.assertThrows;

import java.util.NoSuchElementException;

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
