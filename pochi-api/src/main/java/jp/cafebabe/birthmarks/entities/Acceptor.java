package jp.cafebabe.birthmarks.entities;

public interface Acceptor<T> {
    void accept(Visitor<T> visitor);
}
