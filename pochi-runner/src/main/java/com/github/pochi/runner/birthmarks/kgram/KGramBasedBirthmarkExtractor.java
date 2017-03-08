package com.github.pochi.runner.birthmarks.kgram;

import org.objectweb.asm.ClassVisitor;

import com.github.pochi.runner.birthmarks.AbstractBirthmarkExtractor;
import com.github.pochi.runner.birthmarks.PochiClassVisitor;
import com.github.pochi.runner.birthmarks.entities.BirthmarkType;
import com.github.pochi.runner.config.Configuration;

public class KGramBasedBirthmarkExtractor extends AbstractBirthmarkExtractor{
    private int kvalue;

    public KGramBasedBirthmarkExtractor(int kvalue) {
        super(new BirthmarkType(kvalue + "-gram"));
        this.kvalue = kvalue;
    }

    @Override
    public PochiClassVisitor visitor(ClassVisitor parent, Configuration context) {
        return new KGramBasedBirthmarkExtractVisitor(parent, context, type(), kvalue);
    }
}
