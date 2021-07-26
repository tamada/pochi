package jp.cafebabe.pochi.birthmarks.verbs;

import org.junit.Test;

import static jp.cafebabe.pochi.birthmarks.verbs.MethodNameUtil.verb;
import static jp.cafebabe.pochi.birthmarks.verbs.MethodNameUtil.extractVerb;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MethodNameUtilTest {
    @Test
    public void testVerb() throws Exception {
        assertThat(verb("initialized").get(), is("initialize"));
        assertThat(verb("getSample").get(), is("get"));
        assertThat(verb("equals").get(), is("equal"));
        assertThat(verb("testExtractVerb").get(), is("test"));
        assertThat(verb("extractVerb").get(), is("extract"));
        assertThat(verb("isSecret").get(), is("be"));
    }

    @Test
    public void testExtractVerb() throws Exception {
        assertThat(extractVerb("init"), is("init"));
        assertThat(extractVerb("getSample"), is("get"));
        assertThat(extractVerb("equals"), is("equals"));
        assertThat(extractVerb("testExtractVerb"), is("test"));
        assertThat(extractVerb("extractVerb"), is("extract"));
        assertThat(extractVerb("isSecret"), is("is"));
    }
}
