import jp.cafebabe.birthmarks.BirthmarkParser;
import jp.cafebabe.birthmarks.comparators.ComparatorBuilder;
import jp.cafebabe.birthmarks.extractors.ExtractorBuilder;
import jp.cafebabe.birthmarks.pairs.PairMatcherBuilder;

module jp.cafebabe.pochi {
    requires jp.cafebabe.birthmarks;
    requires jp.cafebabe.izumo;
    requires jp.cafebabe.nasubi;
    requires java.logging;

    uses PairMatcherBuilder;
    uses BirthmarkParser;
    uses ExtractorBuilder;
    uses ComparatorBuilder;

    provides ExtractorBuilder with
            jp.cafebabe.pochi.kgram.KGramBasedExtractorBuilder,
            jp.cafebabe.pochi.uc.UsedClassesExtractorBuilder;

    provides ComparatorBuilder with
            jp.cafebabe.pochi.comparators.DiceIndexComparatorBuilder,
            jp.cafebabe.pochi.comparators.EditDistanceComparatorBuilder,
            jp.cafebabe.pochi.comparators.JaccardIndexComparatorBuilder,
            jp.cafebabe.pochi.comparators.SimpsonIndexComparatorBuilder;

    exports jp.cafebabe.pochi;
    exports jp.cafebabe.pochi.comparators;
    exports jp.cafebabe.pochi.extractors;
    exports jp.cafebabe.pochi.parsers;
    exports jp.cafebabe.pochi.kgram;
    exports jp.cafebabe.pochi.uc;
}