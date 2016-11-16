package com.github.ebis.birthmarks;

import java.util.Map;
import java.util.Objects;

import com.github.ebis.birthmarks.computers.Computer;
import com.github.ebis.birthmarks.computers.Similarity;
import com.github.kunai.entries.ClassName;

public class Pair<T> {
    private ExtractionResult<T> birthmark1;
    private ExtractionResult<T> birthmark2;

    public Pair(ExtractionResult<T> b1, ExtractionResult<T> b2){
        if(b1 == null || b2 == null){
            throw new NullPointerException("pairs are null");
        }
        this.birthmark1 = b1;
        this.birthmark2 = b2;
    }

    public boolean isSamePair(Pair<T> pair){
        return isSamePair(pair.birthmark1, pair.birthmark2);
    }

    public boolean isSamePair(ExtractionResult<T> b1, ExtractionResult<T> b2){
        return (b1 == birthmark1 && b2 == birthmark2) ||
                (b2 == birthmark1 && b1 == birthmark1);
    }

    public Similarity compute(Computer computer){
        return computer.compare(birthmark1.birthmark(), birthmark2.birthmark());
    }

    public boolean isMatchPair(Map<ClassName, ClassName> map){
        ClassName cn1 = birthmark1.identifier().className();
        ClassName cn2 = birthmark2.identifier().className();

        return Objects.equals(map.get(cn1), cn2) || Objects.equals(map.get(cn2), cn1);
    }

    public boolean isSameNameButDifferentLocation(){
        return birthmark1.isSameClassName(birthmark2) 
                && !birthmark1.isSameLocation(birthmark2);
    }
}
