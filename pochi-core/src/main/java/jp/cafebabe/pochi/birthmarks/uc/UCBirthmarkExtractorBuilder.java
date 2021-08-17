package jp.cafebabe.pochi.birthmarks.uc;

import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.extractors.PochiClassVisitor;
import jp.cafebabe.pochi.util.VisitorSupplier;

public class UCBirthmarkExtractorBuilder extends UsedClassesExtractorBuilder {

    @Override
    public BirthmarkType type() {
        return BirthmarkType.of("uc");
    }

    @Override
    public VisitorSupplier<PochiClassVisitor<String>> visitorSupplier() {
        return (parent, type, config) -> new UCBirthmarkExtractVisitor(parent, type, config);
    }
}
