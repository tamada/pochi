package com.github.pochi.runner.birthmarks.parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.pochi.birthmarks.ParserType;
import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.entities.BirthmarkType;
import com.github.pochi.birthmarks.entities.Metadata;
import com.github.pochi.kunai.entries.ClassName;
import com.github.pochi.kunai.entries.Entry;
import com.github.pochi.runner.birthmarks.AbstractParser;
import com.github.pochi.runner.birthmarks.FailedSources;
import com.github.pochi.runner.birthmarks.URLFormatException;
import com.github.pochi.runner.util.LogHelper;

public class DefaultParser extends AbstractParser{
    private FailedSources failedSources = new FailedSources();

    public DefaultParser(Configuration config) {
        super(new ParserType("Default"), config);
    }

    @Override
    public List<Birthmark> parseEntry(Entry entry){
        try(BufferedReader in = new BufferedReader(new InputStreamReader(entry.openStream()))){
            return readLines(in.lines());
        } catch(IOException e){
            LogHelper.warn(this, e);
            failedSources.add(Metadata.build(entry));
        }
        return new ArrayList<>();
    }

    private List<Birthmark> readLines(Stream<String> stream) throws IOException{
        return stream.map(this::readLine)
                .collect(Collectors.toList());
    }

    private Birthmark readLine(String line){
        String[] items = line.split(",");
        return new Birthmark(buildMetadata(items[0], items[1], items[2]), 
                buildElements(Arrays.stream(items).skip(3)));
    }

    private Metadata buildMetadata(String className, String uri, String type){
        try {
            return new Metadata(new ClassName(className), new URI(uri), new BirthmarkType(type));
        } catch (URISyntaxException e) {
            throw new URLFormatException(e);
        }
    }

    @Override
    public Stream<Metadata> failedSources() {
        return failedSources.stream();
    }
}
