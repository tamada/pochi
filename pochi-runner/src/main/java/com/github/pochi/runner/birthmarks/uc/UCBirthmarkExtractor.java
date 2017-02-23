package com.github.pochi.runner.birthmarks.uc;

import org.objectweb.asm.ClassVisitor;

import com.github.pochi.runner.birthmarks.AbstractBirthmarkExtractor;
import com.github.pochi.runner.birthmarks.PochiClassVisitor;
import com.github.pochi.runner.birthmarks.entities.BirthmarkType;
import com.github.pochi.runner.config.Configuration;

public class UCBirthmarkExtractor extends AbstractBirthmarkExtractor{

    public UCBirthmarkExtractor() {
        super(new BirthmarkType("uc"));
    }

    @Override
    public PochiClassVisitor visitor(ClassVisitor parent, Configuration context) {
        return new UCBirthmarkExtractVisitor(parent, context, type());
    }
}
