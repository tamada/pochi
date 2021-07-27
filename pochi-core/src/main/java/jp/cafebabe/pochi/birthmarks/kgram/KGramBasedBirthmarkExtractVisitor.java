package jp.cafebabe.pochi.birthmarks.kgram;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.entities.Elements;
import jp.cafebabe.birthmarks.entities.Metadata;
import jp.cafebabe.birthmarks.extractors.PochiClassVisitor;
import jp.cafebabe.kunai.entries.Entry;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class KGramBasedBirthmarkExtractVisitor extends PochiClassVisitor<String> {
    private Map<String, List<Integer>> opcodes = new LinkedHashMap<>();
    private KGramBuilder<Integer> builder;

    public KGramBasedBirthmarkExtractVisitor(ClassVisitor parent, BirthmarkType type, Configuration context, int kvalue) {
        super(parent, type, context);
        builder = new KGramBuilder<>(kvalue);
    }

    @Override
    public Birthmark<String> build(Entry entry) {
        Metadata metadata = Metadata.build(entry, type());
        return new Birthmark<>(metadata, buildElements(opcodes));
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

    private Elements<String> buildElements(Map<String, List<Integer>> map){
        return map.values()
                .stream().map(this::toElements)
                .reduce(Elements.listElements(), Elements::merge);
    }

    private Elements<String> toElements(List<Integer> list){
        return Elements.listElements(builder.build(list)
                .map(kgram -> kgram.toString()));
    }
}
