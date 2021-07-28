import jp.cafebabe.pochi.birthmarks.uc.FUCBirthmarkExtractorBuilder;

/**
 * <p>
 * The module <code>jp.cafebabe.pochi</code> contains the implementation classes of the APIs defined in the module <code>{@link jp.cafebabe.birthmarks}</code>.
 * </p><p>
 * The implementation classes are shown below.
 * </p>
 *
 * <dl>
 *   <dt>{@link jp.cafebabe.birthmarks.extractors.ExtractorBuilder}</dt>
 *   <dd>
 *     The difference of the implementation classes shows the type of birthmarks.
 *     In this version of <strong>pochi</strong>, two type of birthmarks, k-gram, and UC.
 *   </dd>
 *   <dd>
 *     <ul>
 *       <li>{@link jp.cafebabe.pochi.birthmarks.kgram.KGramBasedExtractorBuilder}</li>
 *       <li>{@link jp.cafebabe.pochi.birthmarks.uc.UsedClassesExtractorBuilder}</li>
 *     </ul>
 *   </dd>
 *   <dt>{@link jp.cafebabe.birthmarks.pairs.PairMatcherBuilder}</dt>
 *   <dd>
 *     <ul>
 *       <li>{@link jp.cafebabe.pochi.pairs.builders.GuessedPairMatcherBuilder}</li>
 *       <li>{@link jp.cafebabe.pochi.pairs.builders.RoundRobinPairMatcherBuilder}</li>
 *       <li>{@link jp.cafebabe.pochi.pairs.builders.RoundRobinWithSamePairMatcherBuilder}</li>
 *     </ul>
 *   </dd>
 *   <dt>{@link jp.cafebabe.birthmarks.comparators.ComparatorBuilder}</dt>
 *   <dd>
 *     <ul>
 *       <li>{@link jp.cafebabe.pochi.comparators.DiceIndexComparatorBuilder}</li>
 *       <li>{@link jp.cafebabe.pochi.comparators.EditDistanceComparatorBuilder}</li>
 *       <li>{@link jp.cafebabe.pochi.comparators.JaccardIndexComparatorBuilder}</li>
 *       <li>{@link jp.cafebabe.pochi.comparators.SimpsonIndexComparatorBuilder}</li>
 *     </ul>
 *   </dd>
 *   <dt>{@link jp.cafebabe.birthmarks.BirthmarkParser}</dt>
 *   <dd>
 *     {@link jp.cafebabe.pochi.parsers.DefaultParser} is the only implementation class of {@link jp.cafebabe.birthmarks.BirthmarkParser}.
 *   </dd>
 * </dl>
 *
 * @author Haruaki Tamada
 */
module jp.cafebabe.pochi {
    requires jp.cafebabe.birthmarks;
    requires java.logging;
    requires org.apache.opennlp.tools;

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
            jp.cafebabe.pochi.birthmarks.uc.UCBirthmarkExtractorBuilder,
            FUCBirthmarkExtractorBuilder,
            jp.cafebabe.pochi.birthmarks.verbs.VerbsOfMethodsExtractorBuilder;

    provides jp.cafebabe.birthmarks.comparators.ComparatorBuilder with
            jp.cafebabe.pochi.comparators.DiceIndexComparatorBuilder,
            jp.cafebabe.pochi.comparators.EditDistanceComparatorBuilder,
            jp.cafebabe.pochi.comparators.JaccardIndexComparatorBuilder,
            jp.cafebabe.pochi.comparators.SimpsonIndexComparatorBuilder,
            jp.cafebabe.pochi.comparators.CosineComparatorBuilder;

    exports jp.cafebabe.pochi;
    exports jp.cafebabe.pochi.comparators;
    exports jp.cafebabe.pochi.extractors;
    exports jp.cafebabe.pochi.pairs;
    exports jp.cafebabe.pochi.parsers;
    exports jp.cafebabe.pochi.birthmarks.kgram;
    exports jp.cafebabe.pochi.birthmarks.uc;
    exports jp.cafebabe.pochi.util;
}
