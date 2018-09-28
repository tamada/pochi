package jp.cafebabe.pochi.runner.birthmarks.comparators;

import java.net.URI;
import java.util.stream.Stream;

import jp.cafebabe.pochi.birthmarks.entities.Birthmark;
import jp.cafebabe.pochi.birthmarks.entities.BirthmarkType;
import jp.cafebabe.pochi.birthmarks.entities.Element;
import jp.cafebabe.pochi.birthmarks.entities.Elements;
import jp.cafebabe.pochi.birthmarks.entities.Metadata;
import jp.cafebabe.pochi.kunai.entries.ClassName;

public class BirthmarkBuilderHelper {

    public Birthmark buildBirthmark(String name, Stream<String> elements) throws Exception{
        Metadata metadata = new Metadata(new ClassName(name), new URI("somelocation"), BirthmarkType.UNKNOWN);
        return new Birthmark(metadata, new Elements(elements.map(Element::new)));
    }
}
