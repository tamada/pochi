package jp.cafebabe.pochi.birthmarks.uc;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.extractors.AbstractExtractor;
import jp.cafebabe.birthmarks.extractors.PochiClassVisitor;
import org.objectweb.asm.ClassVisitor;

public class UsedClassesExtractor extends AbstractExtractor {

    public UsedClassesExtractor(Configuration config) {
        super(new BirthmarkType("uc"), config);
    }

    @Override
    public PochiClassVisitor visitor(ClassVisitor parent) {
        return new UCBirthmarkExtractVisitor(parent, configuration(), type());
    }
}
