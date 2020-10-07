module jp.cafebabe.pochi {
    requires jp.cafebabe.birthmarks;
    requires java.logging;

    uses jp.cafebabe.birthmarks.pairs.PairMatcherBuilder;
    uses jp.cafebabe.birthmarks.extractors.ExtractorBuilder;
    uses jp.cafebabe.birthmarks.comparators.ComparatorBuilder;
    uses jp.cafebabe.birthmarks.BirthmarkParser;

    provides jp.cafebabe.birthmarks.pairs.PairMatcherBuilder with
            jp.cafebabe.pochi.pairs.builders.GuessedPairMatcherBuilder,
            jp.cafebabe.pochi.pairs.builders.RoundRobinPairMatcherBuilder,
            jp.cafebabe.pochi.pairs.builders.RoundRobinWithSamePairMatcherBuilder;

    provides jp.cafebabe.birthmarks.extractors.ExtractorBuilder with
            jp.cafebabe.pochi.birthmarks.kgram.KGramBasedExtractorBuilder,
            jp.cafebabe.pochi.birthmarks.uc.UsedClassesExtractorBuilder;

    provides jp.cafebabe.birthmarks.comparators.ComparatorBuilder with
            jp.cafebabe.pochi.comparators.DiceIndexComparatorBuilder,
            jp.cafebabe.pochi.comparators.EditDistanceComparatorBuilder,
            jp.cafebabe.pochi.comparators.JaccardIndexComparatorBuilder,
            jp.cafebabe.pochi.comparators.SimpsonIndexComparatorBuilder;

    exports jp.cafebabe.pochi;
    exports jp.cafebabe.pochi.comparators;
    exports jp.cafebabe.pochi.extractors;
    exports jp.cafebabe.pochi.pairs;
    exports jp.cafebabe.pochi.parsers;
    exports jp.cafebabe.pochi.birthmarks.kgram;
    exports jp.cafebabe.pochi.birthmarks.uc;
    exports jp.cafebabe.pochi.util;
}