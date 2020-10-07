package jp.cafebabe.pochi.birthmarks.kgram;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.extractors.Extractor;
import jp.cafebabe.birthmarks.extractors.ExtractorBuilder;

public class KGramBasedExtractorBuilder implements ExtractorBuilder {
    private int kvalue;

    public KGramBasedExtractorBuilder() {
        this(3);
    }

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
