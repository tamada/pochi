package jp.cafebabe.pochi.birthmarks.verbs;

import io.vavr.control.Try;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.entities.Elements;
import jp.cafebabe.birthmarks.entities.Metadata;
import jp.cafebabe.birthmarks.extractors.PochiClassVisitor;
import jp.cafebabe.kunai.entries.Entry;
import net.sf.extjwnl.data.POS;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import net.sf.extjwnl.dictionary.Dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Haruaki TAMADA
 */
public class VerbsOfMethodsExtractVisitor extends PochiClassVisitor<String> {
    private List<String> verbs = new ArrayList<>();
    private Dictionary dictionary;

    public VerbsOfMethodsExtractVisitor(ClassVisitor visitor, BirthmarkType type, Configuration context){
        super(visitor, type, context);
        Try.of(() -> dictionary = Dictionary.getDefaultResourceInstance())
                .getOrElseThrow(throwable -> new InternalError(throwable));
    }

    @Override
    public Birthmark<String> build(Entry entry){
        Metadata source = Metadata.build(entry, type());
        return new Birthmark<String>(source, Elements.of(verbs.stream()));
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
                                     String signature, String[] exceptions){
        putVerbsIfNeeded(name);
        return super.visitMethod(access, name, desc, signature, exceptions);
    }

    private void putVerbsIfNeeded(String methodName) {
        Optional<String> verb = extractVerb(methodName);
        verb.ifPresent(v -> verbs.add(v));
    }

    private Optional<String> extractVerb(String methodName) {
        String verb = extractVerbImpl(methodName);
        try{
            System.out.printf("verb: %s (%s)%n", verb, dictionary.getIndexWord(POS.VERB, verb));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return Try.of(() -> dictionary.getIndexWord(POS.VERB, verb))
                .map(word -> Optional.of(word.getLemma()))
                .getOrElse(Optional.empty());
    }

    String extractVerbImpl(String name) {
        return new String(name.chars()
                .takeWhile(value -> value >= 'a' && value <= 'z')
                .collect(() -> new StringBuilder(),
                        (sb, value) -> sb.append((char)value),
                        (sb1, sb2) -> sb1.append(sb2)));
    }

    String extractVerbImpl2(String name) {
        return splitCamelCase(name)[0];
    }

    // https://akisute3.hatenablog.com/entry/20111217/1324109628
    private String[] splitCamelCase(String name) {
        return name.split("(?<=[A-Z])(?=[A-Z][a-z])|(?<=[a-z])(?=[A-Z])");
    }
}
