package jp.cafebabe.pochi.birthmarks.kgram;

import java.util.List;

import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 
 * @author Haruaki TAMADA
 */
public class OpcodeExtractionMethodVisitor extends MethodVisitor{
    private List<Integer> opcodes;

    public OpcodeExtractionMethodVisitor(MethodVisitor visitor,
            List<Integer> opcodes){
        super(Opcodes.ASM5, visitor);
        this.opcodes = opcodes;
    }

    @Override
    public void visitFieldInsn(int opcode, String owner,
            String name, String desc){
        opcodes.add(opcode);
        super.visitFieldInsn(opcode, owner, name, desc);
    }

    @Override
    public void visitIincInsn(int var, int increment){
        opcodes.add(Opcodes.IINC);
        super.visitIincInsn(var, increment);
    }

    @Override
    public void visitInsn(int opcode){
        opcodes.add(opcode);
        super.visitInsn(opcode);
    }

    @Override
    public void visitIntInsn(int opcode, int operand){
        opcodes.add(opcode);
        super.visitIntInsn(opcode, operand);
    }

    @Override
    public void visitJumpInsn(int opcode, Label label){
        opcodes.add(opcode);
        super.visitJumpInsn(opcode, label);
    }

    @Override
    public void visitLdcInsn(Object value){
        opcodes.add(Opcodes.LDC);
        super.visitLdcInsn(value);
    }

    @Override
    public void visitLookupSwitchInsn(Label defaultHandle, int[] keys,
            Label[] labels){
        opcodes.add(Opcodes.LOOKUPSWITCH);
        super.visitLookupSwitchInsn(defaultHandle, keys, labels);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner,
            String name, String desc, boolean itf){
        opcodes.add(opcode);
        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }

    @Override
    public void visitMultiANewArrayInsn(String desc, int dims){
        opcodes.add(Opcodes.MULTIANEWARRAY);
        super.visitMultiANewArrayInsn(desc, dims);
    }

    @Override
    public void visitTableSwitchInsn(int min, int max,
            Label defaultLabel, Label... labels){
        opcodes.add(Opcodes.TABLESWITCH);
        super.visitTableSwitchInsn(min, max, defaultLabel, labels);
    }

    @Override
    public void visitTypeInsn(int opcode, String desc){
        opcodes.add(opcode);
        super.visitTypeInsn(opcode, desc);
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs){
        opcodes.add(Opcodes.INVOKEDYNAMIC);
        super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
    }

    @Override
    public void visitVarInsn(int opcode, int var){
        opcodes.add(opcode);
        super.visitVarInsn(opcode, var);
    }
}
