package jp.cafebabe.birthmarks.extractors;

import jp.cafebabe.birthmarks.TaskBuilder;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.BirthmarkType;

public interface ExtractorBuilder extends TaskBuilder<BirthmarkType> {
    @Override
    BirthmarkType type();

    @Override
    Extractor build(Configuration config);
}
