package jp.cafebabe.birthmarks.extractors;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.kunai.entries.Entry;

public abstract class PochiClassVisitor<T> extends ClassVisitor{
    private Configuration context;
    private BirthmarkType type;

    public PochiClassVisitor(ClassVisitor parent, Configuration context, BirthmarkType type) {
        super(Opcodes.ASM5, parent);
        this.context = context;
        this.type = type;
    }

    public BirthmarkType type(){
        return type;
    }

    public final Configuration context(){
        return context;
    }

    public abstract Birthmark<T> build(Entry entry);
}
