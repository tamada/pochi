package com.github.pochi.runner.birthmarks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.pochi.kunai.entries.ClassName;
import com.github.pochi.kunai.entries.Entry;
import com.github.pochi.runner.birthmarks.entities.Birthmark;
import com.github.pochi.runner.birthmarks.entities.BirthmarkType;
import com.github.pochi.runner.birthmarks.entities.Element;
import com.github.pochi.runner.birthmarks.entities.Elements;
import com.github.pochi.runner.birthmarks.entities.Metadata;
import com.github.pochi.runner.config.Configuration;

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
