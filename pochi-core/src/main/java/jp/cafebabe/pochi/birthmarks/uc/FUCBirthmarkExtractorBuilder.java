package jp.cafebabe.pochi.birthmarks.uc;

import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.entities.Frequency;
import jp.cafebabe.birthmarks.extractors.PochiClassVisitor;
import jp.cafebabe.pochi.util.VisitorSupplier;

public class FUCBirthmarkExtractorBuilder extends UsedClassesExtractorBuilder {

    @Override
    public BirthmarkType type() {
        return new BirthmarkType("fuc");
    }

    @Override
    public VisitorSupplier<PochiClassVisitor<Frequency>> visitorSupplier() {
        return (parent, type, config) -> new FUCBirhtmarkExtractVisitor(parent, type, config);
    }
}
