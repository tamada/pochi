package jp.cafebabe.pochi.birthmarks.verbs;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.entities.Elements;
import jp.cafebabe.birthmarks.entities.Metadata;
import jp.cafebabe.birthmarks.extractors.PochiClassVisitor;
import jp.cafebabe.kunai.entries.Entry;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Haruaki TAMADA
 */
public class VerbsOfMethodsExtractVisitor extends PochiClassVisitor<String> {
    private List<String> verbs = new ArrayList<>();

    public VerbsOfMethodsExtractVisitor(ClassVisitor visitor, BirthmarkType type, Configuration context){
        super(visitor, type, context);
    }

    @Override
    public Birthmark<String> build(Entry entry){
        Metadata source = Metadata.build(entry, type());
        return new Birthmark<String>(source,
                Elements.listElements(verbs.stream().sorted()));
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
                                     String signature, String[] exceptions){
        putVerbsIfNeeded(name);
        return super.visitMethod(access, name, desc, signature, exceptions);
    }

    private void putVerbsIfNeeded(String methodName) {
        Optional<String> verb = MethodNameUtil.verb(methodName);
        verb.ifPresent(v -> verbs.add(v));
    }
}
