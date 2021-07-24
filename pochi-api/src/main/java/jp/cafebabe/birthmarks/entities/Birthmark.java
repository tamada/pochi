package jp.cafebabe.birthmarks.entities;

import java.io.Serializable;
import java.net.URI;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.function.Predicate;

import jp.cafebabe.kunai.entries.ClassName;

public class Birthmark<T> implements Acceptor<T>, Serializable{
    private static final long serialVersionUID = -2383836180204233756L;

    private Elements<T> elements;
    private Metadata metadata;

    public Birthmark(Metadata metadata, Elements<T> elements){
        this.metadata = metadata;
        this.elements = elements;
    }

    public ClassName className(){
        return metadata.className();
    }

    public boolean contains(T element){
        return elements.contains(element);
    }

    public int elementCount(){
        return elements.size();
    }

    public void forEach(Consumer<T> consumer){
        elements.forEach(consumer);
    }

    public Birthmark<T> filter(Predicate<T> predicate){
        return new Birthmark<>(metadata(),
                elements.filter(predicate));
    }

    public boolean isSame(ClassName name){
        return metadata.isSame(name);
    }

    public boolean isSame(URI location){
        return metadata.isSame(location);
    }

    public boolean isSame(BirthmarkType type){
        return metadata.isSame(type);
    }

    public URI location(){
        return metadata.location();
    }

    public Metadata metadata(){
        return metadata;
    }

    public BirthmarkType type(){
        return metadata.type();
    }

    @Override
    public void accept(Visitor<T> visitor) {
        metadata.accept(visitor);
        elements.accept(visitor);
    }

    @Override
    public String toString() {
        return new StringJoiner(",").add(metadata.toString())
                .add(elements.toString())
                .toString();
    }
}
