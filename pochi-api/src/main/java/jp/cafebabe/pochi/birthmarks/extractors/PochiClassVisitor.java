package jp.cafebabe.pochi.birthmarks.extractors;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import jp.cafebabe.pochi.birthmarks.config.Configuration;
import jp.cafebabe.pochi.birthmarks.entities.Birthmark;
import jp.cafebabe.pochi.birthmarks.entities.BirthmarkType;
import jp.cafebabe.pochi.kunai.entries.Entry;

public abstract class PochiClassVisitor extends ClassVisitor{
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

    public abstract Birthmark build(Entry entry);
}
