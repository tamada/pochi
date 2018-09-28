package jp.cafebabe.pochi.runner.birthmarks.kgram;

import org.objectweb.asm.ClassVisitor;

import jp.cafebabe.pochi.birthmarks.config.Configuration;
import jp.cafebabe.pochi.birthmarks.entities.BirthmarkType;
import jp.cafebabe.pochi.birthmarks.extractors.AbstractExtractor;
import jp.cafebabe.pochi.birthmarks.extractors.PochiClassVisitor;

public class KGramBasedBirthmarkExtractor extends AbstractExtractor{
    private int kvalue;

    public KGramBasedBirthmarkExtractor(int kvalue, Configuration config) {
        super(new BirthmarkType(kvalue + "-gram"), config);
        this.kvalue = kvalue;
    }

    @Override
    public PochiClassVisitor visitor(ClassVisitor parent) {
        return new KGramBasedBirthmarkExtractVisitor(parent, configuration(), type(), kvalue);
    }
}
