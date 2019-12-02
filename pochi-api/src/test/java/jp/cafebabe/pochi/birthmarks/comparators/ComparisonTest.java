package jp.cafebabe.pochi.birthmarks.comparators;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import jp.cafebabe.pochi.birthmarks.entities.Birthmark;
import jp.cafebabe.pochi.birthmarks.entities.BirthmarkType;
import jp.cafebabe.pochi.birthmarks.entities.Element;
import jp.cafebabe.pochi.birthmarks.entities.Elements;
import jp.cafebabe.pochi.birthmarks.entities.Metadata;
import jp.cafebabe.pochi.kunai.entries.ClassName;

public class ComparisonTest {
    private Birthmark birthmark1;
    private Birthmark birthmark2;

    @Before
    public void buildBirthmarks() throws URISyntaxException {
        birthmark1 = buildBirthmark("className1", "url1", "type1", Arrays.asList("e1", "e2", "e3"));
        birthmark2 = buildBirthmark("className2", "url1", "type1", Arrays.asList("e1", "e2", "e3"));
    }

    private Birthmark buildBirthmark(String className, String uri, String type, List<String> list) throws URISyntaxException {
        Metadata metadata = new Metadata(new ClassName(className), new URI(uri), new BirthmarkType(type));
        Elements elements = new Elements(list.stream().map(e -> new Element(e)));
        return new Birthmark(metadata, elements);
    }

    @Test
    public void testComparisons() {
        Comparison comparison = new Comparison(birthmark1, birthmark2, new Similarity(0.75));

        assertThat(comparison.left(), is(birthmark1));
        assertThat(comparison.right(), is(birthmark2));
        assertThat(comparison.similarity().value, is(closeTo(0.75, 1E-6)));
    }

    @Test
    public void testToString() {
        Comparison comparison = new Comparison(birthmark1, birthmark2, new Similarity(0.75));

        assertThat(comparison.toString(), is("className1,className2,0.75"));
    }
    
    @Test
    public void testThreshold() {
        Comparison comparison1 = new Comparison(birthmark1, birthmark2, new Similarity(0.75));
        Comparison comparison2 = new Comparison(birthmark1, birthmark2, new Similarity(0.5));
        Comparison comparison3 = new Comparison(birthmark1, birthmark2, new Similarity(0.15));

        Threshold threshold1 = new Threshold(0.3);
        assertThat(comparison1.isStolen(threshold1), is(true));
        assertThat(comparison1.isInconclusive(threshold1), is(false));
        assertThat(comparison1.isInnocent(threshold1), is(false));

        assertThat(comparison2.isStolen(threshold1), is(false));
        assertThat(comparison2.isInconclusive(threshold1), is(true));
        assertThat(comparison2.isInnocent(threshold1), is(false));

        assertThat(comparison3.isStolen(threshold1), is(false));
        assertThat(comparison3.isInconclusive(threshold1), is(false));
        assertThat(comparison3.isInnocent(threshold1), is(true));

        Threshold threshold2 = new Threshold(0.2);
        assertThat(comparison1.isStolen(threshold2), is(false));
        assertThat(comparison1.isInconclusive(threshold2), is(true));
        assertThat(comparison1.isInnocent(threshold2), is(false));

        assertThat(comparison2.isStolen(threshold2), is(false));
        assertThat(comparison2.isInconclusive(threshold2), is(true));
        assertThat(comparison2.isInnocent(threshold2), is(false));

        assertThat(comparison3.isStolen(threshold2), is(false));
        assertThat(comparison3.isInconclusive(threshold2), is(false));
        assertThat(comparison3.isInnocent(threshold2), is(true));

        Threshold threshold3 = new Threshold(0.1);
        assertThat(comparison1.isStolen(threshold3), is(false));
        assertThat(comparison1.isInconclusive(threshold3), is(true));
        assertThat(comparison1.isInnocent(threshold3), is(false));
        
        assertThat(comparison2.isStolen(threshold3), is(false));
        assertThat(comparison2.isInconclusive(threshold3), is(true));
        assertThat(comparison2.isInnocent(threshold3), is(false));

        assertThat(comparison3.isStolen(threshold3), is(false));
        assertThat(comparison3.isInconclusive(threshold3), is(true));
        assertThat(comparison3.isInnocent(threshold3), is(false));
    }
}
