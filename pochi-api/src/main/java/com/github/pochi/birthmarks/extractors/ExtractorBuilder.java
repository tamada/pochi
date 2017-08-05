package com.github.pochi.birthmarks.extractors;

import com.github.pochi.birthmarks.TaskBuilder;
import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.entities.BirthmarkType;

public interface ExtractorBuilder extends TaskBuilder<BirthmarkType> {
    @Override
    BirthmarkType type();

    @Override
    Extractor build(Configuration config);
}
