package com.github.ebis.birthmarks.extractor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import com.github.ebis.birthmarks.Birthmark;

public abstract class EbisClassVisitor<T> extends ClassVisitor {

    public EbisClassVisitor() {
        super(Opcodes.ASM5);
    }

    public abstract Birthmark<T> getBirthmark();
}
