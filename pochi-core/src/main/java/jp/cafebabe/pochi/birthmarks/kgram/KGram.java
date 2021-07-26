package jp.cafebabe.pochi.birthmarks.kgram;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Override
    public int hashCode() {
        return Objects.hash("KGram", values);
    }

    @Override
    public boolean equals(Object other) {
        Boolean flag = other instanceof KGram
                && getClass().isAssignableFrom(other.getClass());
        if(flag) {
            @SuppressWarnings("unchecked")
            KGram<T> kgram = (KGram<T>)other;
            return values.equals(kgram.values);
        }
        return false;
    }
}
