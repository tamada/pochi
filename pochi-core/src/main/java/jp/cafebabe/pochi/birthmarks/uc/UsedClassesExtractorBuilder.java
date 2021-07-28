package jp.cafebabe.pochi.birthmarks.uc;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.extractors.Extractor;
import jp.cafebabe.birthmarks.extractors.ExtractorBuilder;
import jp.cafebabe.birthmarks.extractors.PochiClassVisitor;
import jp.cafebabe.pochi.birthmarks.VisitorBirthmarkExtractor;
import jp.cafebabe.pochi.util.VisitorSupplier;

public abstract class UsedClassesExtractorBuilder implements ExtractorBuilder {

    @Override
    public abstract BirthmarkType type();

    @Override
    public Extractor build(Configuration config) {
        return new VisitorBirthmarkExtractor<String>(type(), config, visitorSupplier());
    }

    public abstract <T> VisitorSupplier<PochiClassVisitor<T>> visitorSupplier();
}
