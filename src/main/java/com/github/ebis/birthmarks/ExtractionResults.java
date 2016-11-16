package com.github.ebis.birthmarks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import com.github.ebis.birthmarks.pairmaker.PairMaker;

public class ExtractionResults<T> implements Iterable<ExtractionResult<T>>{
    private List<ExtractionResult<T>> list = new ArrayList<>();

    public ExtractionResults(List<ExtractionResult<T>> results){
        list.addAll(results);
    }

    public Stream<Pair<T>> comparingStream(PairMaker<T> detector){
        return detector.stream(this);
    }

    public Stream<ExtractionResult<T>> stream(){
        return list.stream();
    }

    @Override
    public Iterator<ExtractionResult<T>> iterator() {
        return list.iterator();
    }
}
