module jp.cafebabe.pochi.izumo{
    requires jp.cafebabe.pochi.api;

    uses jp.cafebabe.pochi.birthmarks.pairs.PairMatcherBuilder;
    provides jp.cafebabe.pochi.birthmarks.pairs.PairMatcherBuilder with jp.cafebabe.pochi.izumo.builders.GuessedPairMatcherBuilder,
            jp.cafebabe.pochi.izumo.builders.RoundRobinPairMatcherBuilder,
            jp.cafebabe.pochi.izumo.builders.RoundRobinWithSamePairMatcherBuilder;
}