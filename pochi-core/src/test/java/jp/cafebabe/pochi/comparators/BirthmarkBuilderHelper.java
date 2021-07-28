package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.entities.Elements;
import jp.cafebabe.birthmarks.entities.Metadata;
import jp.cafebabe.kunai.entries.ClassName;

import java.net.URI;
import java.util.stream.Stream;

public class BirthmarkBuilderHelper {
    public Birthmark<String> buildBirthmark(String name, Stream<String> elements) throws Exception{
        Metadata metadata = new Metadata(new ClassName(name), new URI("somelocation"), BirthmarkType.UNKNOWN);
        return new Birthmark<>(metadata, Elements.listElements(elements));
    }
}
