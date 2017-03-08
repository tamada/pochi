package com.github.pochi.runner.birthmarks.comparators;

import java.net.URI;
import java.util.stream.Stream;

import com.github.pochi.kunai.entries.ClassName;
import com.github.pochi.runner.birthmarks.entities.Birthmark;
import com.github.pochi.runner.birthmarks.entities.BirthmarkType;
import com.github.pochi.runner.birthmarks.entities.Element;
import com.github.pochi.runner.birthmarks.entities.Elements;
import com.github.pochi.runner.birthmarks.entities.Metadata;

public class BirthmarkBuilderHelper {

    public Birthmark buildBirthmark(String name, Stream<String> elements) throws Exception{
        Metadata metadata = new Metadata(new ClassName(name), new URI("somelocation"), BirthmarkType.UNKNOWN);
        return new Birthmark(metadata, new Elements(elements.map(Element::new)));
    }
}
