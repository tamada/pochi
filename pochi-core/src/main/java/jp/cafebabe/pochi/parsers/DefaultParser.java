package jp.cafebabe.pochi.parsers;

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

import jp.cafebabe.birthmarks.ParserType;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.entities.Metadata;
import jp.cafebabe.pochi.AbstractParser;
import jp.cafebabe.pochi.FailedSources;
import jp.cafebabe.pochi.URLFormatException;
import jp.cafebabe.kunai.entries.ClassName;
import jp.cafebabe.kunai.entries.Entry;
import jp.cafebabe.pochi.util.LogHelper;

public class DefaultParser extends AbstractParser{
    private FailedSources failedSources = new FailedSources();

    public DefaultParser(Configuration config) {
        super(new ParserType("default"), config);
    }

    @Override
    public List<Birthmark<String>> parseEntry(Entry entry){
        try(BufferedReader in = new BufferedReader(new InputStreamReader(entry.openStream()))){
            return readLines(in.lines());
        } catch(IOException e){
            LogHelper.warn(this, e);
            failedSources.add(Metadata.build(entry));
        }
        return new ArrayList<>();
    }

    private List<Birthmark<String>> readLines(Stream<String> stream) throws IOException{
        return stream.map(this::readLine)
                .collect(Collectors.toList());
    }

    private Birthmark<String> readLine(String line){
        String[] items = line.split(",");
        return new Birthmark<>(buildMetadata(items[0], items[1], items[2]),
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
