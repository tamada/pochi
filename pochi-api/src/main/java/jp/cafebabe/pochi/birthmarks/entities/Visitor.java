package jp.cafebabe.pochi.birthmarks.entities;

import java.net.URI;

import jp.cafebabe.pochi.kunai.entries.ClassName;

public interface Visitor {
    void visitBirthmarks(Birthmarks target);

    void visitBirthmark(Birthmark birthmark);

    void visitMetadata(BirthmarkType type, ClassName name, URI uri);

    void visitElement(Element element);
}
