package com.github.pochi.kunai.source;

import static org.objectweb.asm.ClassReader.SKIP_CODE;
import static org.objectweb.asm.ClassReader.SKIP_DEBUG;
import static org.objectweb.asm.ClassReader.SKIP_FRAMES;

import java.io.IOException;
import java.io.InputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import com.github.pochi.kunai.entries.ClassName;

class ClassNameExtractVisitor extends ClassVisitor{
    private String className;

    public ClassNameExtractVisitor() {
        super(Opcodes.ASM5);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces){
        this.className = name;
    }

    public static ClassName extractClassName(InputStream in) throws IOException{
        ClassNameExtractVisitor visitor = new ClassNameExtractVisitor();
        new ClassReader(in).accept(visitor, SKIP_CODE | SKIP_DEBUG | SKIP_FRAMES);
        return new ClassName(visitor.className);
    }
}
