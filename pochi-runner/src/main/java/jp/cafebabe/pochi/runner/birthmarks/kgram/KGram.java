package jp.cafebabe.pochi.runner.birthmarks.kgram;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jp.cafebabe.pochi.birthmarks.entities.Element;

public class KGram<T extends Serializable> implements Serializable{
    private static final long serialVersionUID = 171133658354079736L;

    private List<T> values;

    public KGram(Stream<T> stream){
        this.values = stream.collect(Collectors.toList());
    }

    public KGram(T[] originals){
        this.values = Arrays.stream(originals)
                .collect(Collectors.toList());
    }

    public T indexOf(int index){
        return values.get(index);
    }

    public int size(){
        return values.size();
    }

    @Override
    public String toString(){
        return values.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }

    public Element toElement(){
        return new Element(toString());
    }
}
