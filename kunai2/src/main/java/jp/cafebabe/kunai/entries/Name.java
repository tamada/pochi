package jp.cafebabe.kunai.entries;

import java.io.Serializable;
import java.util.Objects;

public class Name implements Serializable, Comparable<Name> {
    private static final long serialVersionUID = -3739333182235166571L;

    private String name;

    public Name(String name){
        this.name = name;
    }

    String name(){
        return name;
    }

    @Override
    public int compareTo(Name other) {
        return name().compareTo(other.name());
    }

    public boolean matches(String pattern) {
        return name.matches(pattern);
    }

    public boolean contains(String partialString) {
        return name.contains(partialString);
    }

    public boolean startsWith(String pattern) {
        return name.startsWith(pattern);
    }

    public boolean endsWith(String pattern) {
        return name.endsWith(pattern);
    }

    @Override
    public boolean equals(Object other){
        return other instanceof Name
            && Objects.equals(name,
                    ((Name)other).name);
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

    @Override
    public String toString(){
        return name;
    }
}
