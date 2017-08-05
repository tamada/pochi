package com.github.pochi.runner.birthmarks.uc;

import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.entities.BirthmarkType;
import com.github.pochi.birthmarks.extractors.Extractor;
import com.github.pochi.birthmarks.extractors.ExtractorBuilder;

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
