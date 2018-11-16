package jp.cafebabe.pochi.birthmarks.kgram;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import jp.cafebabe.pochi.birthmarks.config.Configuration;
import jp.cafebabe.pochi.birthmarks.entities.Birthmark;
import jp.cafebabe.pochi.birthmarks.entities.BirthmarkType;
import jp.cafebabe.pochi.birthmarks.entities.Elements;
import jp.cafebabe.pochi.birthmarks.entities.Metadata;
import jp.cafebabe.pochi.birthmarks.extractors.PochiClassVisitor;
import jp.cafebabe.pochi.kunai.entries.Entry;

public class KGramBasedBirthmarkExtractVisitor extends PochiClassVisitor {
    private Map<String, List<Integer>> opcodes = new LinkedHashMap<>();
    private KGramBuilder<Integer> builder;

    public KGramBasedBirthmarkExtractVisitor(ClassVisitor parent, Configuration context, BirthmarkType type, int kvalue) {
        super(parent, context, type);
        builder = new KGramBuilder<>(kvalue);
    }

    @Override
    public Birthmark build(Entry entry) {
        Metadata metadata = Metadata.build(entry, type());
        return new Birthmark(metadata, buildElements(opcodes));
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        List<Integer> list = createList(name + desc);
        return createVisitor(access, name, desc, signature, exceptions, list);
    }

    private List<Integer> createList(String key){
        List<Integer> list = opcodes.getOrDefault(key, new ArrayList<>());
        opcodes.put(key, list);
        return list;
    }

    private MethodVisitor createVisitor(int access, String name, String desc, String signature, String[] exceptions, List<Integer> list) {
        MethodVisitor visitor = super.visitMethod(access, name, desc, signature, exceptions);
        return new OpcodeExtractionMethodVisitor(visitor, list);
    }

    private Elements buildElements(Map<String, List<Integer>> map){
        return map.values()
                .stream().map(this::toElements)
                .reduce(Elements.empty(), Elements::merge);
    }

    private Elements toElements(List<Integer> list){
        return new Elements(builder.build(list)
                .map(KGram::toElement));
    }
}
