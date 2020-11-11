package jp.cafebabe.birthmarks.pairs;

import java.io.Serializable;
import java.util.Objects;

public final class PairMatcherType implements Serializable, Comparable<PairMatcherType> {
    private static final long serialVersionUID = -3169312619260242474L;

    private String type;

    public PairMatcherType(String name){
        this.type = name;
    }

    @Override
    public int hashCode(){
        return Objects.hash(type, "matcher");
    }

    @Override
    public boolean equals(Object object){
        return object instanceof PairMatcherType
                && Objects.equals(type,
                        ((PairMatcherType)object).type);
    }

    @Override
    public String toString(){
        return type;
    }

    @Override
    public int compareTo(PairMatcherType other) {
        return type.compareTo(other.type);
    }
}
