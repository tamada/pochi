package jp.cafebabe.birthmarks.comparators;

import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.entities.Elements;
import jp.cafebabe.birthmarks.entities.Metadata;
import jp.cafebabe.kunai.entries.ClassName;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

public class ComparisonTest {
    private Birthmark<String> birthmark1;
    private Birthmark<String> birthmark2;

    @Before
    public void buildBirthmarks() throws URISyntaxException {
        birthmark1 = buildBirthmark("className1", "url1", "type1", Arrays.asList("e1", "e2", "e3"));
        birthmark2 = buildBirthmark("className2", "url1", "type1", Arrays.asList("e1", "e2", "e3"));
    }

    private Birthmark<String> buildBirthmark(String className, String uri, String type, List<String> list) throws URISyntaxException {
        Metadata metadata = new Metadata(new ClassName(className), new URI(uri), BirthmarkType.of(type));
        Elements<String> elements = Elements.listElements(list.stream());
        return new Birthmark<>(metadata, elements);
    }

    @Test
    public void testComparisons() {
        Comparison<String> comparison = new Comparison<>(birthmark1, birthmark2, new Similarity(0.75));

        assertThat(comparison.left(), is(birthmark1));
        assertThat(comparison.right(), is(birthmark2));
        assertThat(comparison.similarity().value, is(closeTo(0.75, 1E-6)));
    }

    @Test
    public void testToString() {
        Comparison<String> comparison = new Comparison<>(birthmark1, birthmark2, new Similarity(0.75));

        assertThat(comparison.toString(), is("className1,className2,0.75"));
    }

    @Test
    public void testThresholdCase0() {
        Comparison<String> comparison = new Comparison<>(birthmark1, birthmark2, new Similarity(0.6));
        Threshold threshold = new Threshold(0.75);

        assertThat(comparison.isStolen(threshold), is(false));
        assertThat(comparison.isInconclusive(threshold), is(true));
        assertThat(comparison.isInnocent(threshold), is(false));
    }

    @Test
    public void testThresholdCase1() {
        Comparison<String> comparison1 = new Comparison<>(birthmark1, birthmark2, new Similarity(0.75));
        Threshold threshold1 = new Threshold(0.3);

        assertThat(comparison1.isStolen(threshold1), is(true));
        assertThat(comparison1.isInconclusive(threshold1), is(false));
        assertThat(comparison1.isInnocent(threshold1), is(false));
    }

    @Test
    public void testThresholdCase2() {
        Comparison<String> comparison2 = new Comparison<>(birthmark1, birthmark2, new Similarity(0.5));
        Threshold threshold1 = new Threshold(0.3);

        assertThat(comparison2.isStolen(threshold1), is(true));
        assertThat(comparison2.isInconclusive(threshold1), is(false));
        assertThat(comparison2.isInnocent(threshold1), is(true));
    }

    @Test
    public void testThresholdCase3() {
        Comparison<String> comparison3 = new Comparison<>(birthmark1, birthmark2, new Similarity(0.15));
        Threshold threshold1 = new Threshold(0.3);

        assertThat(comparison3.isStolen(threshold1), is(false));
        assertThat(comparison3.isInconclusive(threshold1), is(false));
        assertThat(comparison3.isInnocent(threshold1), is(true));
    }

    @Test
    public void testThresholdCase4() {
        Comparison<String> comparison1 = new Comparison<>(birthmark1, birthmark2, new Similarity(0.75));
        Threshold threshold2 = new Threshold(0.2);

        assertThat(comparison1.isStolen(threshold2), is(true));
        assertThat(comparison1.isInconclusive(threshold2), is(false));
        assertThat(comparison1.isInnocent(threshold2), is(true));
    }

    @Test
    public void testThresholdCase5() {
        Comparison<String> comparison2 = new Comparison<>(birthmark1, birthmark2, new Similarity(0.5));
        Threshold threshold2 = new Threshold(0.2);

        assertThat(comparison2.isStolen(threshold2), is(true));
        assertThat(comparison2.isInconclusive(threshold2), is(false));
        assertThat(comparison2.isInnocent(threshold2), is(true));
    }

    @Test
    public void testThresholdCase6() {
        Comparison<String> comparison3 = new Comparison<>(birthmark1, birthmark2, new Similarity(0.15));
        Threshold threshold2 = new Threshold(0.2);

        assertThat(comparison3.isStolen(threshold2), is(false));
        assertThat(comparison3.isInconclusive(threshold2), is(false));
        assertThat(comparison3.isInnocent(threshold2), is(true));
    }

    @Test
    public void testThresholdCase7() {
        Comparison<String> comparison1 = new Comparison<>(birthmark1, birthmark2, new Similarity(0.75));
        Threshold threshold3 = new Threshold(0.1);

        assertThat(comparison1.isStolen(threshold3), is(true));
        assertThat(comparison1.isInconclusive(threshold3), is(false));
        assertThat(comparison1.isInnocent(threshold3), is(true));
    }

    @Test
    public void testThresholdCase8() {
        Comparison<String> comparison2 = new Comparison<>(birthmark1, birthmark2, new Similarity(0.5));
        Threshold threshold3 = new Threshold(0.1);

        assertThat(comparison2.isStolen(threshold3), is(true));
        assertThat(comparison2.isInconclusive(threshold3), is(false));
        assertThat(comparison2.isInnocent(threshold3), is(true));
    }

    @Test
    public void testThresholdCase9() {
        Comparison<String> comparison3 = new Comparison<>(birthmark1, birthmark2, new Similarity(0.15));
        Threshold threshold3 = new Threshold(0.1);

        assertThat(comparison3.isStolen(threshold3), is(true));
        assertThat(comparison3.isInconclusive(threshold3), is(false));
        assertThat(comparison3.isInnocent(threshold3), is(true));
    }
}
