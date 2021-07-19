package jp.cafebabe.pochi.comparators;

import java.net.URI;
import java.util.stream.Stream;

import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.birthmarks.entities.Elements;
import jp.cafebabe.birthmarks.entities.Metadata;
import jp.cafebabe.kunai.entries.ClassName;

public class BirthmarkBuilderHelper {
    public Birthmark buildBirthmark(String name, Stream<String> elements) throws Exception{
        Metadata metadata = new Metadata(new ClassName(name), new URI("somelocation"), BirthmarkType.UNKNOWN);
        return new Birthmark(metadata, new Elements(elements.map(Element::new)));
    }
}
