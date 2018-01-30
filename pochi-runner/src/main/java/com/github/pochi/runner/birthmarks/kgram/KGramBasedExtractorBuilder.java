package com.github.pochi.runner.birthmarks.kgram;

import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.entities.BirthmarkType;
import com.github.pochi.birthmarks.extractors.Extractor;
import com.github.pochi.birthmarks.extractors.ExtractorBuilder;

public class KGramBasedExtractorBuilder implements ExtractorBuilder {
    private int kvalue;

    public KGramBasedExtractorBuilder(int kvalue) {
        this.kvalue = kvalue;
    }

    @Override
    public BirthmarkType type() {
        return new BirthmarkType(kvalue + "-gram");
    }

    @Override
    public Extractor build(Configuration config) {
        return new KGramBasedBirthmarkExtractor(kvalue, config);
    }
}
