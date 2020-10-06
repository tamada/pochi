package jp.cafebabe.pochi.birthmarks.uc;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.extractors.Extractor;
import jp.cafebabe.birthmarks.extractors.ExtractorBuilder;

public class UsedClassesExtractorBuilder implements ExtractorBuilder {

    @Override
    public BirthmarkType type() {
        return new BirthmarkType("uc");
    }

    @Override
    public Extractor build(Configuration config) {
        return new UsedClassesExtractor(config);
    }

}
