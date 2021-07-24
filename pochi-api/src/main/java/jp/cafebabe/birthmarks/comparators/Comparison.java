package jp.cafebabe.birthmarks.comparators;

import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Pair;

import java.io.Serializable;

public class Comparison<T> implements Serializable {
    private Pair<Birthmark<T>> pair;
    private Similarity similarity;

    public Comparison(Birthmark<T> left, Birthmark<T> right, Similarity similarity){
        this(new Pair<>(left, right), similarity);
    }

    public Comparison(Pair<Birthmark<T>> pair, Similarity similarity){
        this.pair = pair;
        this.similarity = similarity;
    }

    public Birthmark<T> left(){
        return pair.left();
    }

    public Birthmark<T> right(){
        return pair.right();
    }

    public Similarity similarity(){
        return similarity;
    }

    public boolean isInnocent(Threshold threshold){
        return threshold.isInnocent(similarity);
    }

    public boolean isStolen(Threshold threshold){
        return threshold.isStolen(similarity);
    }

    public boolean isInconclusive(Threshold threshold){
        return threshold.isInconclusive(similarity);
    }

    @Override
    public String toString(){
        return String.format("%s,%s,%s", 
                left().className(),
                right().className(), similarity);
    }
}
