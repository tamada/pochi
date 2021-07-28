package jp.cafebabe.pochi.birthmarks.verbs;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EnglishDictionaryTest {
    private EnglishDictionary dic = EnglishDictionary.defaultInstance();

    @Test
    public void testVerbChecking() throws Exception {
        assertThat(dic.isVerb("run"), is(true));
        assertThat(dic.isVerb("equals"), is(false));
        assertThat(dic.isVerb("be"), is(true));
        assertThat(dic.isVerb("initialize"), is(true));
        assertThat(dic.isVerb("init"), is(true));
        assertThat(dic.isVerb("to"), is(true));
        assertThat(dic.isVerb("new"), is(true));
        assertThat(dic.isVerb("name"), is(true));
        assertThat(dic.isVerb("apple"), is(false));
        assertThat(dic.isVerb("banana"), is(false));
        assertThat(dic.isVerb("public"), is(false));
    }

    @Test
    public void testLemmatizer() throws Exception {
        assertThat(dic.lemmatize("copied").get(), is("copy"));
        assertThat(dic.lemmatize("running").get(), is("run"));
        assertThat(dic.lemmatize("equals").get(), is("equal"));
        assertThat(dic.lemmatize("runs").get(), is("run"));
        assertThat(dic.lemmatize("run").get(), is("run"));
        assertThat(dic.lemmatize("ran").get(), is("run"));
        assertThat(dic.lemmatize("has").get(), is("have"));
        assertThat(dic.lemmatize("is").get(), is("be"));
        assertThat(dic.lemmatize("to").get(), is("to"));
        assertThat(dic.lemmatize("new").get(), is("new"));
        assertThat(dic.lemmatize("init").get(), is("initialize"));
    }

    @Test
    public void testLemmatizeNoun() throws Exception {
        assertThat(dic.lemmatize("apple"), is(Optional.empty()));
    }
}
