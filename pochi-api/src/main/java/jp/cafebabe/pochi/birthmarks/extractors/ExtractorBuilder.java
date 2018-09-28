package jp.cafebabe.pochi.birthmarks.extractors;

import jp.cafebabe.pochi.birthmarks.TaskBuilder;
import jp.cafebabe.pochi.birthmarks.config.Configuration;
import jp.cafebabe.pochi.birthmarks.entities.BirthmarkType;

public interface ExtractorBuilder extends TaskBuilder<BirthmarkType> {
    @Override
    BirthmarkType type();

    @Override
    Extractor build(Configuration config);
}
