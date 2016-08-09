package com.github.kunai.source;

import java.io.IOException;
import java.io.InputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import com.github.kunai.entries.ClassName;

class ClassNameExtractVisitor extends ClassVisitor{
    private String className;

    public ClassNameExtractVisitor() {
        super(Opcodes.ASM5);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces){
        this.className = name;
    }

    public String getClassName(){
        return className;
    }

    public static ClassName extractClassName(InputStream in) throws IOException{
        ClassNameExtractVisitor visitor = new ClassNameExtractVisitor();
        ClassReader reader = new ClassReader(in);
        reader.accept(visitor, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
        return new ClassName(visitor.getClassName());
    }
}
