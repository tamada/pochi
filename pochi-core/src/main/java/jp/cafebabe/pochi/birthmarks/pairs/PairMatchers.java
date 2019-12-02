package jp.cafebabe.pochi.birthmarks.pairs;

import java.util.stream.Stream;

import jp.cafebabe.pochi.birthmarks.Tasks;
import jp.cafebabe.pochi.birthmarks.entities.Birthmark;
import jp.cafebabe.pochi.birthmarks.pairs.PairMatcher;
import jp.cafebabe.pochi.birthmarks.pairs.PairMatcherType;

public class PairMatchers extends Tasks<PairMatcherType>{

    public PairMatchers(Stream<PairMatcher<Birthmark>> stream){
        super(stream);
    }

    public PairMatcherType[] availableTypes(){
        return stream()
                .map(maker -> maker.type())
                .toArray(size -> new PairMatcherType[size]);
    }
}
