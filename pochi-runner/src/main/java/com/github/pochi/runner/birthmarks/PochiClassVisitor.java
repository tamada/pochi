package com.github.pochi.runner.birthmarks;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.entities.BirthmarkType;
import com.github.pochi.kunai.entries.Entry;

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
