package jp.cafebabe.pochi.birthmarks;

import jp.cafebabe.pochi.birthmarks.comparators.ComparatorBuilder;
import jp.cafebabe.pochi.birthmarks.comparators.ComparatorType;
import jp.cafebabe.pochi.birthmarks.entities.BirthmarkType;
import jp.cafebabe.pochi.birthmarks.extractors.ExtractorBuilder;
import jp.cafebabe.pochi.birthmarks.comparators.ComparatorBuilders;
import jp.cafebabe.pochi.birthmarks.extractors.ExtractorBuilders;

public class BirthmarkSystem {
    private ExtractorBuilders extractors = new ExtractorBuilders();
    private ComparatorBuilders comparators = new ComparatorBuilders();

    public ExtractorBuilder extractor(BirthmarkType type){
        return extractors.builder(type);
    }

    public BirthmarkType[] availableExtractors(){
        return extractors.availableTypes()
                .toArray(BirthmarkType[]::new);
    }

    public void register(ExtractorBuilder builder) {
        extractors.register(builder);
    }

    public void register(ComparatorBuilder builder) {
        comparators.register(builder);
    }

    public ComparatorBuilder comparator(ComparatorType type){
        return comparators.builder(type);
    }

    public ComparatorType[] availableComparators(){
        return comparators.availableTypes()
                .toArray(ComparatorType[]::new);
    }
}
