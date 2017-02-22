package com.github.pochi.nasubi;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Test;

import com.github.pochi.nasubi.Exceptions;

public class ExceptionsTest {
    @Test
    public void testThrows1() throws Exception{
        assertThat(Exceptions.isThrowed(new Object(), target -> new Object()), is(true));
        assertThat(Exceptions.isThrowed(new Object(), target -> { throw new Exception(); }), is(false));
    }

    @Test
    public void testThrows2() throws Exception{
        assertThat(Exceptions.isThrowed(new Object(), new Object(),
                (target1, target2) -> new Object()), is(true));
        assertThat(Exceptions.isThrowed(new Object(), new Object(),
                (target1, target2) -> { throw new Exception(); }), is(false));
    }

    @Test
    public void testPredicate1() throws Exception{
        assertThat(Exceptions.isThrowedCondition(new Object(), argument -> true), is(true));
        assertThat(Exceptions.isThrowedCondition(new Object(), argument -> false), is(false));
        assertThat(Exceptions.isThrowedCondition(new Object(),
                argument -> { throw new Exception(); }), is(false));
    }

    @Test
    public void testPredicate2() throws Exception{
        assertThat(Exceptions.isThrowedCondition(new Object(), new Object(), (argument1, argument2) -> true), is(true));
        assertThat(Exceptions.isThrowedCondition(new Object(), new Object(), (argument1, argument2) -> false), is(false));
        assertThat(Exceptions.isThrowedCondition(new Object(), new Object(),
                (argument1, argument2) -> { throw new Exception(); }), is(false));
    }

    @Test
    public void testMap() throws Exception{
        assertThat(Exceptions.map("a", s1 -> s1 + s1), is(Optional.of("aa")));
        assertThat(Exceptions.map("a", s1 -> null), is(Optional.empty()));
        assertThat(Exceptions.map("a", s1 -> { throw new Exception(); }),
                is(Optional.empty()));
    }

    @Test
    public void testMap2() throws Exception{
        assertThat(Exceptions.map("a", "b", (s1, s2) -> s1 + s2), is(Optional.of("ab")));
        assertThat(Exceptions.map("a", "b", (s1, s2) -> null), is(Optional.empty()));
        assertThat(Exceptions.map("a", "b", (s1, s2) -> { throw new Exception(); }),
                is(Optional.empty()));
    }
}
