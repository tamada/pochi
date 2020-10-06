package jp.cafebabe.pochi.birthmarks.uc;

import java.util.Arrays;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.entities.Metadata;
import jp.cafebabe.birthmarks.extractors.PochiClassVisitor;
import jp.cafebabe.kunai.entries.Entry;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.signature.SignatureReader;

/**
 *
 * @author Haruaki TAMADA
 */
public class UCBirthmarkExtractVisitor extends PochiClassVisitor {
    private UCBirthmarkHelper helper;

    public UCBirthmarkExtractVisitor(ClassVisitor visitor, Configuration context, BirthmarkType type){
        super(visitor, context, type);
        this.helper = new UCBirthmarkHelper(context);
    }

    @Override
    public Birthmark build(Entry entry){
        Metadata source = Metadata.build(entry, type());
        return new Birthmark(source, helper.build());
    }

    @Override
    public void visit(int version, int access, String name, String signature,
            String superName, String[] interfaces){
        addSignatureClass(signature);
        helper.add(superName);
        helper.addAll(interfaces);
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc,
            String signature, Object value){
        addDescriptor(desc);
        addSignatureClass(signature);
        return super.visitField(access, name, desc, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
                                     String signature, String[] exceptions){
        helper.addAll(exceptions);
        addMethodDescriptor(desc);
        addSignatureClass(signature);
        return new UCBirthmarkExtractMethodVisitor(
                super.visitMethod(access, name, desc, signature, exceptions), helper, this);
    }

    void addSignatureClass(String signature){
        if(signature == null)
            return;
        SignatureReader reader = new SignatureReader(signature);
        reader.accept(new UCBirthmarkSignatureWriter(helper));
    }

    void addMethodDescriptor(String desc){
        helper.add(Type.getReturnType(desc));
        Type[] args = Type.getArgumentTypes(desc);
        Arrays.stream(args)
        .forEach(helper::add);
    }

    void addDescriptor(String desc){
        helper.add(Type.getType(desc));
    }
}
