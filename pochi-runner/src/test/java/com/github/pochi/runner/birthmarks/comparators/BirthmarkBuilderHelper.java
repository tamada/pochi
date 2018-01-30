package com.github.pochi.runner.birthmarks.comparators;

import java.net.URI;
import java.util.stream.Stream;

import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.entities.BirthmarkType;
import com.github.pochi.birthmarks.entities.Element;
import com.github.pochi.birthmarks.entities.Elements;
import com.github.pochi.birthmarks.entities.Metadata;
import com.github.pochi.kunai.entries.ClassName;

public class BirthmarkBuilderHelper {

    public Birthmark buildBirthmark(String name, Stream<String> elements) throws Exception{
        Metadata metadata = new Metadata(new ClassName(name), new URI("somelocation"), BirthmarkType.UNKNOWN);
        return new Birthmark(metadata, new Elements(elements.map(Element::new)));
    }
}
