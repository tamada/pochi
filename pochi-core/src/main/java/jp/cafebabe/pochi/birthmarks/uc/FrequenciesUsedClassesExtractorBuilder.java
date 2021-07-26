package jp.cafebabe.pochi.birthmarks.uc;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.extractors.Extractor;
import jp.cafebabe.birthmarks.extractors.ExtractorBuilder;
import jp.cafebabe.pochi.birthmarks.VisitorBirthmarkExtractor;

public class FrequenciesUsedClassesExtractorBuilder implements ExtractorBuilder {

    @Override
    public BirthmarkType type() {
        return new BirthmarkType("fuc");
    }

    @Override
    public Extractor build(Configuration config) {
        return new VisitorBirthmarkExtractor<Frequency>(type(), config,
                (parent, type, configuration) -> new FUCBirhtmakrExtractVisitor(parent, type, configuration));
    }
}