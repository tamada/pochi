package com.github.pochi.runner.birthmarks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.stream.Stream;

import com.github.pochi.kunai.entries.ClassName;
import com.github.pochi.kunai.entries.Entry;
import com.github.pochi.kunai.source.DataSource;
import com.github.pochi.runner.birthmarks.entities.Birthmark;
import com.github.pochi.runner.birthmarks.entities.BirthmarkType;
import com.github.pochi.runner.birthmarks.entities.Birthmarks;
import com.github.pochi.runner.birthmarks.entities.Element;
import com.github.pochi.runner.birthmarks.entities.Elements;
import com.github.pochi.runner.birthmarks.entities.Metadata;
import com.github.pochi.runner.config.Configuration;
import com.github.pochi.runner.util.TimeredList;

public class DefaultBirthmarkParser implements BirthmarkParser{
    private BirthmarkType type;
    private FailedSources failedSources = new FailedSources();

    @Override
    public BirthmarkType type(){
        return type;
    }

    @Override
    public TimeredList<Birthmark> parseForStream(DataSource source, Configuration context){
        return source.stream()
                .filter(entry -> entry.endsWith(".csv"))
                .map(entry -> parseEntry(entry, context))
                .reduce((first, second) -> first.merge(second)).get();
    }

    public Birthmarks parse(DataSource source, Configuration context){
        TimeredList<Birthmark> stream = parseForStream(source, context);
        // do termination operation before calling ```type()``` 
        return new Birthmarks(stream);
    }


    @Override
    public TimeredList<Birthmark> parseEntry(Entry entry, Configuration context){
        try(BufferedReader in = new BufferedReader(new InputStreamReader(entry.openStream()))){
            return readLines(in.lines());
        } catch(IOException e){
            failedSources.add(Metadata.build(entry));
        }
        return new TimeredList<>(Stream.of());
    }

    private TimeredList<Birthmark> readLines(Stream<String> stream) throws IOException{
        return new TimeredList<>(stream.map(this::readLine));
    }

    private Birthmark readLine(String line){
        String[] items = line.split(",");
        return new Birthmark(buildMetadata(items[0], items[1], items[2]), buildElements(items));
    }

    private Elements buildElements(String[] items){
        return new Elements(Arrays.stream(items, 3, items.length)
                .map(item -> new Element(item)));
    }

    private Metadata buildMetadata(String className, String uri, String type){
        try {
            return new Metadata(new ClassName(className), new URI(uri), new BirthmarkType(type));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Stream<Metadata> failedSources() {
        return failedSources.stream();
    }
}
