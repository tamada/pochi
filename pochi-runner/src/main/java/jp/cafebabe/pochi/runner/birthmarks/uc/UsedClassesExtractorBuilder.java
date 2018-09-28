package jp.cafebabe.pochi.runner.birthmarks.uc;

import jp.cafebabe.pochi.birthmarks.config.Configuration;
import jp.cafebabe.pochi.birthmarks.entities.BirthmarkType;
import jp.cafebabe.pochi.birthmarks.extractors.Extractor;
import jp.cafebabe.pochi.birthmarks.extractors.ExtractorBuilder;

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
