package jp.cafebabe.pochi.birthmarks.kgram;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.extractors.AbstractExtractor;
import jp.cafebabe.birthmarks.extractors.PochiClassVisitor;
import org.objectweb.asm.ClassVisitor;

public class KGramBasedBirthmarkExtractor extends AbstractExtractor {
    private int kvalue;

    public KGramBasedBirthmarkExtractor(int kvalue, Configuration config) {
        super(BirthmarkType.of(kvalue + "-gram"), config);
        this.kvalue = kvalue;
    }

    @Override
    public PochiClassVisitor<String> visitor(ClassVisitor parent) {
        return new KGramBasedBirthmarkExtractVisitor(parent, type(), configuration(), kvalue);
    }
}
