package com.github.pochi.runner.birthmarks;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import com.github.pochi.kunai.entries.Entry;
import com.github.pochi.runner.birthmarks.entities.Birthmark;
import com.github.pochi.runner.config.Configuration;

public abstract class PochiClassVisitor extends ClassVisitor{
    private Configuration context;

    public PochiClassVisitor(ClassVisitor parent, Configuration context) {
        super(Opcodes.ASM5, parent);
        this.context = context;
    }

    public final Configuration context(){
        return context;
    }

    public abstract Birthmark build(Entry entry);
}
