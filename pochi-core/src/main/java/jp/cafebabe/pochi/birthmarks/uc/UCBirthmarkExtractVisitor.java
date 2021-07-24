package jp.cafebabe.pochi.birthmarks.uc;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.entities.Metadata;
import jp.cafebabe.kunai.entries.Entry;
import org.objectweb.asm.ClassVisitor;

/**
 *
 * @author Haruaki TAMADA
 */
public class UCBirthmarkExtractVisitor extends UsedClassesBirthmarkExtractVisitor<String> {
    public UCBirthmarkExtractVisitor(ClassVisitor visitor, Configuration context, BirthmarkType type){
        super(visitor, context, type);
    }

    @Override
    public Birthmark<String> build(Entry entry){
        Metadata source = Metadata.build(entry, type());
        return new Birthmark<>(source, helper.build((k, v) -> k));
    }
}
