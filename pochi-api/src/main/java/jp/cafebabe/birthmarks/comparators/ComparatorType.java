package jp.cafebabe.birthmarks.comparators;

import java.io.Serializable;
import java.util.Objects;

public class ComparatorType implements Serializable, Comparable<ComparatorType> {
    private static final long serialVersionUID = 5510440148918700265L;

    private String type;

    public static ComparatorType of(String type) {
        return new ComparatorType(type);
    }

    private ComparatorType(String type){
        this.type = type;
    }

    @Override
    public String toString(){
        return type;
    }

    @Override
    public int hashCode(){
        return Objects.hash(type, getClass().getName());
    }

    @Override
    public boolean equals(Object other){
        return other instanceof ComparatorType
                && Objects.equals(type, ((ComparatorType)other).type);
    }

    @Override
    public int compareTo(ComparatorType other) {
        return type.compareTo(other.type);
    }
}
