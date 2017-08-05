package com.github.pochi.runner.birthmarks.uc;

import org.objectweb.asm.ClassVisitor;

import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.entities.BirthmarkType;
import com.github.pochi.birthmarks.extractors.AbstractExtractor;
import com.github.pochi.birthmarks.extractors.PochiClassVisitor;

public class UsedClassesExtractor extends AbstractExtractor{

    public UsedClassesExtractor(Configuration config) {
        super(new BirthmarkType("uc"), config);
    }

    @Override
    public PochiClassVisitor visitor(ClassVisitor parent) {
        return new UCBirthmarkExtractVisitor(parent, configuration(), type());
    }
}
