package jp.cafebabe.pochi.birthmarks.uc;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.entities.Frequency;
import jp.cafebabe.birthmarks.entities.Metadata;
import jp.cafebabe.kunai.entries.Entry;
import org.objectweb.asm.ClassVisitor;

public class FUCBirhtmarkExtractVisitor extends UsedClassesBirthmarkExtractVisitor<Frequency> {
    public FUCBirhtmarkExtractVisitor(ClassVisitor parent, BirthmarkType type, Configuration configuration) {
        super(parent, type, configuration);
    }

    @Override
    public Birthmark<Frequency> build(Entry entry) {
        Metadata source = Metadata.build(entry, type());
        return new Birthmark<>(source, helper.frequencies());
    }
}
