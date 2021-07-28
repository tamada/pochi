package jp.cafebabe.pochi.extractors;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import jp.cafebabe.birthmarks.extractors.ExtractorBuilder;
import jp.cafebabe.birthmarks.AbstractTaskBuilders;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.pochi.birthmarks.kgram.KGramBasedExtractorBuilder;
import jp.cafebabe.pochi.birthmarks.uc.FUCBirthmarkExtractorBuilder;
import jp.cafebabe.pochi.birthmarks.uc.UCBirthmarkExtractorBuilder;
import jp.cafebabe.pochi.birthmarks.uc.UsedClassesExtractorBuilder;
import jp.cafebabe.pochi.birthmarks.verbs.VerbsOfMethodsExtractorBuilder;

public class ExtractorBuilders extends AbstractTaskBuilders<BirthmarkType, ExtractorBuilder>{
    public ExtractorBuilders() {
        super(ExtractorBuilder.class);
        register();
    }

    private void register() {
        register(new UCBirthmarkExtractorBuilder());
        register(new FUCBirthmarkExtractorBuilder());
        register(new VerbsOfMethodsExtractorBuilder());
        kgramStream().forEach(this::register);
    }

    private Stream<ExtractorBuilder> kgramStream(){
        return IntStream.rangeClosed(1, 6)
                .mapToObj(KGramBasedExtractorBuilder::new);
    }
}
