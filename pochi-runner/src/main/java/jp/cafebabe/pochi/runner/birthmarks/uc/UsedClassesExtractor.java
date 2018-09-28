package jp.cafebabe.pochi.runner.birthmarks.uc;

import org.objectweb.asm.ClassVisitor;

import jp.cafebabe.pochi.birthmarks.config.Configuration;
import jp.cafebabe.pochi.birthmarks.entities.BirthmarkType;
import jp.cafebabe.pochi.birthmarks.extractors.AbstractExtractor;
import jp.cafebabe.pochi.birthmarks.extractors.PochiClassVisitor;

public class UsedClassesExtractor extends AbstractExtractor{

    public UsedClassesExtractor(Configuration config) {
        super(new BirthmarkType("uc"), config);
    }

    @Override
    public PochiClassVisitor visitor(ClassVisitor parent) {
        return new UCBirthmarkExtractVisitor(parent, configuration(), type());
    }
}
