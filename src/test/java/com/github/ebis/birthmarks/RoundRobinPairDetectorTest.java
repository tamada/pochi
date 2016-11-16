package com.github.ebis.birthmarks;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.github.ebis.birthmarks.pairmaker.PairMaker;
import com.github.ebis.birthmarks.pairmaker.RoundRobinPairMaker;
import com.github.kunai.entries.ClassName;

public class RoundRobinPairDetectorTest {
    private ExtractionResults<String> birthmarks;
    private ExtractionResult<String> b1;
    private ExtractionResult<String> b2;
    private ExtractionResult<String> b3;
    private ExtractionResult<String> b4;


    @Before
    public void setUp(){
        Elements<String> e1 = Elements.<String>build(Stream.of("e11", "e12", "e13"));
        Elements<String> e2 = Elements.<String>build(Stream.of("e21", "e22"));
        Elements<String> e3 = Elements.<String>build(Stream.of("e31", "e32", "e33"));
        Elements<String> e4 = Elements.<String>build(Stream.of("e41", "e42", "e43", "e44"));
        BirthmarkType type = new BirthmarkType("type");
        Identifier dummyId = new Identifier(new ClassName("hoge"), URI.create("./test"));

        b1 = new ExtractionResult<String>(dummyId, new Birthmark<String>(type, e1));
        b2 = new ExtractionResult<String>(dummyId, new Birthmark<String>(type, e2));
        b3 = new ExtractionResult<String>(dummyId, new Birthmark<String>(type, e3));
        b4 = new ExtractionResult<String>(dummyId, new Birthmark<String>(type, e4));

        birthmarks = new ExtractionResults<String>(Arrays.asList(b1, b2, b3, b4));
    }

    @Test
    public void testBasic() throws Exception{
        PairMaker<String> detector = new RoundRobinPairMaker<>();
        Stream<Pair<String>> stream = detector.stream(birthmarks);
        List<Pair<String>> list = stream.collect(Collectors.toList());

        assertThat(list.size(), is(6));
        assertThat(list.get(0).isSamePair(b1, b2), is(true));
        assertThat(list.get(1).isSamePair(b1, b3), is(true));
        assertThat(list.get(2).isSamePair(b1, b4), is(true));

        assertThat(list.get(3).isSamePair(b2, b3), is(true));
        assertThat(list.get(4).isSamePair(b2, b4), is(true));

        assertThat(list.get(5).isSamePair(b3, b4), is(true));
    }
}
