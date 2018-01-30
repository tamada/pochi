package com.github.pochi.birthmarks.entities;

import java.net.URI;

import com.github.pochi.kunai.entries.ClassName;

public interface Visitor {
    void visitBirthmarks(Birthmarks target);

    void visitBirthmark(Birthmark birthmark);

    void visitMetadata(BirthmarkType type, ClassName name, URI uri);

    void visitElement(Element element);
}
