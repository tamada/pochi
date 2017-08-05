package com.github.pochi.runner.birthmarks.kgram;

import org.objectweb.asm.ClassVisitor;

import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.entities.BirthmarkType;
import com.github.pochi.birthmarks.extractors.AbstractExtractor;
import com.github.pochi.birthmarks.extractors.PochiClassVisitor;

public class KGramBasedBirthmarkExtractor extends AbstractExtractor{
    private int kvalue;

    public KGramBasedBirthmarkExtractor(int kvalue, Configuration config) {
        super(new BirthmarkType(kvalue + "-gram"), config);
        this.kvalue = kvalue;
    }

    @Override
    public PochiClassVisitor visitor(ClassVisitor parent) {
        return new KGramBasedBirthmarkExtractVisitor(parent, configuration(), type(), kvalue);
    }
}
