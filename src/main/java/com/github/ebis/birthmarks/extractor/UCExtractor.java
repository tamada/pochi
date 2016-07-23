package com.github.ebis.birthmarks.extractor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import com.github.ebis.Context;
import com.github.ebis.Rules;
import com.github.ebis.birthmarks.Birthmark;
import com.github.ebis.birthmarks.BirthmarkType;
import com.github.ebis.birthmarks.Element;
import com.github.ebis.birthmarks.MutableElements;

public class UCExtractor extends ASMExtractor<String> {
    public UCExtractor() {
        super("uc");
    }

    @Override
    public Element<String> buildElement(String value) {
        return new Element<String>(value);
    }

    private void checkAndAdd(List<String> birthmark, String className, Rules rules) {
        if (rules.anyMatch(className)) {
            birthmark.add(className);
        }
    }

    @Override
    public EbisClassVisitor<String> getVisitor(final Context context) {
        final Rules manager = context.rules();
        final List<String> list = new ArrayList<>();
        return new EbisClassVisitor<String>() {
            private Birthmark<String> birthmark;

            @Override
            public Birthmark<String> getBirthmark() {
                return birthmark;
            }

            @Override
            public void visit(int version, int access, String name, String signature, String superName,
                    String[] interfaces) {

                if (manager.anyMatch(superName)) {
                    list.add(superName);
                }
                Arrays.stream(interfaces)
                    .filter(item -> manager.anyMatch(name))
                    .forEach(item -> list.add(item));

                super.visit(version, access, name, signature, superName, interfaces);
            }

            @Override
            public void visitEnd() {
                MutableElements<String> elem = new MutableElements<>();
                list.stream().forEach(item -> elem.add(item));
                birthmark = new Birthmark<String>(new BirthmarkType("uc"), elem);
            }

            @Override
            public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
                Type type = Type.getType(desc);
                if (type.getSort() == Type.OBJECT) {
                    checkAndAdd(list, type.getClassName(), manager);
                } else if (type.getSort() == Type.ARRAY) {
                    if (type.getElementType().getSort() == Type.OBJECT) {
                        checkAndAdd(list, type.getElementType().getClassName(), manager);
                    }
                }
                return super.visitField(access, name, desc, signature, value);
            }

            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature,
                    String[] exceptions) {
                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        };
    }
}
