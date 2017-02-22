package com.github.pochi.runner.birthmarks;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.stream.Stream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import com.github.pochi.kunai.entries.Entry;
import com.github.pochi.runner.birthmarks.entities.Birthmark;
import com.github.pochi.runner.birthmarks.entities.BirthmarkType;
import com.github.pochi.runner.birthmarks.entities.Metadata;
import com.github.pochi.runner.config.Configuration;

public abstract class AbstractBirthmarkExtractor implements BirthmarkExtractor{
    private BirthmarkType type;
    private FailedSources failedSources = new FailedSources();

    public AbstractBirthmarkExtractor(BirthmarkType type){
        this.type = type;
    }

    @Override
    public Optional<Birthmark> extractEach(Entry entry, Configuration context){
        try{ return Optional.of(extractImpl(entry, context)); }
        catch(IOException e){ }
        failedSources.add(Metadata.build(entry));
        return Optional.empty();
    }

    private Birthmark extractImpl(Entry entry, Configuration configuration) throws IOException{
        try(InputStream in = entry.openStream()){
            PochiClassVisitor visitor = visitor(new ClassWriter(0), configuration);
            return accept(in, entry, visitor);
        }
    }

    private Birthmark accept(InputStream in, Entry entry, PochiClassVisitor visitor) throws IOException{
        ClassReader reader = new ClassReader(in);
        reader.accept(visitor, ClassReader.SKIP_DEBUG);
        return visitor.build(entry);        
    }

    public BirthmarkType type(){
        return type;
    }

    public Stream<Metadata> failedSources(){
        return failedSources.stream();
    }
}
