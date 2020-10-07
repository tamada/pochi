package jp.cafebabe.pochi.comparators;

import java.net.URI;
import java.util.stream.Stream;

import jp.cafebabe.birthmarks.entities.*;
import jp.cafebabe.kunai.entries.ClassName;
import org.junit.Ignore;

public class BirthmarkBuilderHelper {
    public Birthmark buildBirthmark(String name, Stream<String> elements) throws Exception{
        Metadata metadata = new Metadata(new ClassName(name), new URI("somelocation"), BirthmarkType.UNKNOWN);
        return new Birthmark(metadata, new Elements(elements.map(Element::new)));
    }
}
