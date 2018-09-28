package jp.cafebabe.pochi.runner.birthmarks.uc;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.signature.SignatureVisitor;

class UCBirthmarkSignatureWriter extends SignatureVisitor {
    private UCBirthmarkHelper helper;

    public UCBirthmarkSignatureWriter(UCBirthmarkHelper helper){
        super(Opcodes.ASM5);
        this.helper = helper;
    }

    @Override
    public void visitClassType(String classType){
        helper.add(classType);
    }
}
