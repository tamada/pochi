import jp.cafebabe.birthmarks.pairs.PairMatcherBuilder;
import jp.cafebabe.izumo.builders.GuessedPairMatcherBuilder;
import jp.cafebabe.izumo.builders.RoundRobinPairMatcherBuilder;
import jp.cafebabe.izumo.builders.RoundRobinWithSamePairMatcherBuilder;

module jp.cafebabe.izumo{
    requires jp.cafebabe.birthmarks;

    uses PairMatcherBuilder;
    provides PairMatcherBuilder with
            GuessedPairMatcherBuilder,
            RoundRobinPairMatcherBuilder,
            RoundRobinWithSamePairMatcherBuilder;

    exports jp.cafebabe.izumo;
    exports jp.cafebabe.izumo.builders;
}