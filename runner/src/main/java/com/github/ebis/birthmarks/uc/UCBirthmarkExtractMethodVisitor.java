package com.github.ebis.birthmarks.uc;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

class UCBirthmarkExtractMethodVisitor extends MethodVisitor {
    private UCBirthmarkHelper helper;
    private UCBirthmarkExtractVisitor visitor;

    public UCBirthmarkExtractMethodVisitor(MethodVisitor visitor, UCBirthmarkHelper helper, UCBirthmarkExtractVisitor cVisitor) {
        super(Opcodes.ASM5, visitor);
        this.helper = helper;
        this.visitor = cVisitor;
    }

    @Override
    public void visitTypeInsn(int opcode, String desc) {
        Type type = Type.getType("L" + desc + ";");
        helper.add(type);
        super.visitTypeInsn(opcode, desc);
    }

    @Override
    public void visitTryCatchBlock(Label start, Label end, Label handle, String desc) {
        Type type = Type.getType("L" + desc + ";");
        helper.add(type);
        super.visitTryCatchBlock(start, end, handle, desc);
    }

    @Override
    public void visitMultiANewArrayInsn(String desc, int dims) {
        Type type = Type.getType(desc);
        helper.add(type);
        super.visitMultiANewArrayInsn(desc, dims);
    }

//    @Override
//    public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
//        System.out.printf("visitLocalVariable(%s, %s, %s, %d)%n", name, desc, signature, index);
//        helper.add(Type.getType(desc));
//        visitor.addSignatureClass(signature);
//
//        super.visitLocalVariable(name, desc, signature, start, end, index);
//    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        helper.add(owner);
        visitor.addDescriptor(desc);
        super.visitFieldInsn(opcode, owner, name, desc);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        helper.add(owner);
        visitor.addMethodDescriptor(desc);
        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }

}
