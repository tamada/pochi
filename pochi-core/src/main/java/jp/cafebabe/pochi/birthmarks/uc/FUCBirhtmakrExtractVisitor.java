package jp.cafebabe.pochi.birthmarks.uc;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.entities.Elements;
import jp.cafebabe.birthmarks.extractors.PochiClassVisitor;
import jp.cafebabe.kunai.entries.Entry;
import org.objectweb.asm.ClassVisitor;

public class FUCBirhtmakrExtractVisitor extends UCBirthmarkExtractVisitor {
    public FUCBirhtmakrExtractVisitor(ClassVisitor parent, Configuration configuration, BirthmarkType type) {
        super(parent, configuration, type);
    }

    @Override
    public Birthmark build(Entry entry) {
        return null;
    }
}
