package com.github.ebis.birthmarks;

import java.util.stream.Stream;

import com.github.ebis.birthmarks.computers.Computer;
import com.github.ebis.birthmarks.computers.Similarity;
import com.github.ebis.birthmarks.pairmaker.PairMaker;

public class ComparisonResults<T> {
    private Computer computer;
    private ExtractionResults<T> rs;

    public ComparisonResults(ExtractionResults<T> rs, Computer computer){
        this.rs = rs;
        this.computer = computer;
    }

    public Stream<ComparisonResult<T>> stream(PairMaker<T> pd){
        return rs.comparingStream(pd).map(pair -> {
            Similarity sim = pair.compute(computer);
            return new ComparisonResult<T>(pair, sim);
        });
    }
}
