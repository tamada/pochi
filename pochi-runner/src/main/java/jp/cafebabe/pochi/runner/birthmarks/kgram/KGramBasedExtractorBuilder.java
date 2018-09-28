package jp.cafebabe.pochi.runner.birthmarks.kgram;

import jp.cafebabe.pochi.birthmarks.config.Configuration;
import jp.cafebabe.pochi.birthmarks.entities.BirthmarkType;
import jp.cafebabe.pochi.birthmarks.extractors.Extractor;
import jp.cafebabe.pochi.birthmarks.extractors.ExtractorBuilder;

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
