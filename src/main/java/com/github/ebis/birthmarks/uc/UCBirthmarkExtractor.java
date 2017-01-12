package com.github.ebis.birthmarks.uc;

import org.objectweb.asm.ClassVisitor;

import com.github.ebis.birthmarks.AbstractBirthmarkExtractor;
import com.github.ebis.birthmarks.EbisClassVisitor;
import com.github.ebis.birthmarks.entities.BirthmarkType;
import com.github.ebis.config.Configuration;

public class UCBirthmarkExtractor extends AbstractBirthmarkExtractor{

    public UCBirthmarkExtractor() {
        super(new BirthmarkType("uc"));
    }

    @Override
    public EbisClassVisitor visitor(ClassVisitor parent, Configuration context) {
        return new UCBirthmarkExtractVisitor(parent, context);
    }

}
