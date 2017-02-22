package com.github.ebis.birthmarks;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import com.github.ebis.birthmarks.entities.Birthmark;
import com.github.ebis.config.Configuration;
import com.github.kunai.entries.Entry;

public abstract class EbisClassVisitor extends ClassVisitor{
    private Configuration context;

    public EbisClassVisitor(ClassVisitor parent, Configuration context) {
        super(Opcodes.ASM5, parent);
        this.context = context;
    }

    public final Configuration context(){
        return context;
    }

    public abstract Birthmark build(Entry entry);
}
