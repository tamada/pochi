package com.github.ebis.birthmarks;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.stream.Stream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import com.github.ebis.birthmarks.entities.Birthmark;
import com.github.ebis.birthmarks.entities.BirthmarkType;
import com.github.ebis.birthmarks.entities.Metadata;
import com.github.ebis.config.Configuration;
import com.github.kunai.entries.Entry;

public abstract class AbstractBirthmarkExtractor implements BirthmarkExtractor{
    private BirthmarkType type;
    private FailedSources sources = new FailedSources();

    public AbstractBirthmarkExtractor(BirthmarkType type){
        this.type = type;
    }

    @Override
    public Optional<Birthmark> extractEach(Entry entry, Configuration context){
        try{ return Optional.of(extractImpl(entry, context)); }
        catch(IOException e){ }
        sources.add(Metadata.build(entry));
        return Optional.empty();
    }

    private Birthmark extractImpl(Entry entry, Configuration configuration) throws IOException{
        try(InputStream in = entry.getInputStream()){
            EbisClassVisitor visitor = visitor(new ClassWriter(0), configuration);
            return accept(in, entry, visitor);
        }
    }

    private Birthmark accept(InputStream in, Entry entry, EbisClassVisitor visitor) throws IOException{
        ClassReader reader = new ClassReader(in);
        reader.accept(visitor, ClassReader.SKIP_DEBUG);
        return visitor.build(entry);        
    }

    public BirthmarkType type(){
        return type;
    }

    public Stream<Metadata> failedSources(){
        return sources.stream();
    }
}
