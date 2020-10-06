package jp.cafebabe.pochi.pairs;

import java.util.stream.Stream;

import jp.cafebabe.birthmarks.Tasks;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.pairs.PairMatcher;
import jp.cafebabe.birthmarks.pairs.PairMatcherType;

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
