package com.github.pochi.runner.birthmarks.rules;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.github.pochi.runner.birthmarks.rules.Rule;
import com.github.pochi.runner.birthmarks.rules.Rules;

public class RulesTest {
    private Rules rules = new Rules();

    @Before
    public void addDefaultRules(){
        rules.add(new Rule("prefix", "java.util."));
        rules.add(new Rule("prefix", "java.lang."));
    }

    @Test
    public void testBasic(){
        assertThat(rules.anyMatch("java.lang.System"), is(true));
        assertThat(rules.anyMatch("java.io.IOException"), is(false));

        Rule[] array = rules.toArray();
        assertThat(array.length, is(2));
        assertThat(array[0].toString(), is("PREFIX,java.util."));
        assertThat(array[1].toString(), is("PREFIX,java.lang."));
    }

    @Test
    public void testRemove(){
        rules.removeAll();
        assertThat(rules.anyMatch("java.lang.System"), is(false));
        assertThat(rules.anyMatch("java.io.IOException"), is(false));
    }
}
