import jp.cafebabe.birthmarks.BirthmarkParser;
import jp.cafebabe.birthmarks.comparators.ComparatorBuilder;
import jp.cafebabe.birthmarks.extractors.ExtractorBuilder;
import jp.cafebabe.birthmarks.pairs.PairMatcherBuilder;
import jp.cafebabe.pochi.birthmarks.kgram.KGramBasedExtractorBuilder;
import jp.cafebabe.pochi.pairs.builders.GuessedPairMatcherBuilder;
import jp.cafebabe.pochi.pairs.builders.RoundRobinPairMatcherBuilder;
import jp.cafebabe.pochi.pairs.builders.RoundRobinWithSamePairMatcherBuilder;

module jp.cafebabe.pochi {
    requires jp.cafebabe.birthmarks;
    requires java.logging;

    uses PairMatcherBuilder;
    uses BirthmarkParser;
    uses ExtractorBuilder;
    uses ComparatorBuilder;

    provides PairMatcherBuilder with
            RoundRobinPairMatcherBuilder,
            RoundRobinWithSamePairMatcherBuilder,
            GuessedPairMatcherBuilder;

    provides ExtractorBuilder with
            KGramBasedExtractorBuilder,
            jp.cafebabe.pochi.birthmarks.uc.UsedClassesExtractorBuilder;

    provides ComparatorBuilder with
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