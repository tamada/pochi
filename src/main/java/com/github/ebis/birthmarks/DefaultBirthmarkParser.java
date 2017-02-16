package com.github.ebis.birthmarks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.ebis.birthmarks.entities.Birthmark;
import com.github.ebis.birthmarks.entities.BirthmarkType;
import com.github.ebis.birthmarks.entities.Element;
import com.github.ebis.birthmarks.entities.Elements;
import com.github.ebis.birthmarks.entities.Metadata;
import com.github.ebis.config.Configuration;
import com.github.kunai.entries.ClassName;
import com.github.kunai.entries.Entry;

public class DefaultBirthmarkParser implements BirthmarkParser{
    private BirthmarkType type;
    private FailedSources failedSources = new FailedSources();

    @Override
    public BirthmarkType type(){
        return type;
    }

    @Override
    public Stream<Birthmark> parseEntry(Entry entry, Configuration context){
        try(BufferedReader in = new BufferedReader(new InputStreamReader(entry.openStream()))){
            return readLines(in.lines());
        } catch(IOException e){
            failedSources.add(Metadata.build(entry));
        }
        return Stream.of();
    }

    private Stream<Birthmark> readLines(Stream<String> stream) throws IOException{
        // do termination operation before closing BufferedReader.
        List<Birthmark> list = stream.map(line -> readLine(line))
                .collect(Collectors.toList());
        return list.stream();
    }

    private Birthmark readLine(String line){
        String[] items = line.split(",");
        this.type = new BirthmarkType(items[2]);
        return new Birthmark(buildMetadata(items[1], items[0]), buildElements(items));
    }

    private Elements buildElements(String[] items){
        return new Elements(Arrays.stream(items, 3, items.length)
                .map(item -> new Element(item)));
    }

    private Metadata buildMetadata(String uri, String className){
        try {
            return new Metadata(new URI(uri), new ClassName(className));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Stream<Metadata> failedSources() {
        return failedSources.stream();
    }
}
