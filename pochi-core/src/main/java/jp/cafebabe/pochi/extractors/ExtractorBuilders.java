package jp.cafebabe.pochi.extractors;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import jp.cafebabe.birthmarks.extractors.ExtractorBuilder;
import jp.cafebabe.birthmarks.AbstractTaskBuilders;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.pochi.birthmarks.kgram.KGramBasedExtractorBuilder;
import jp.cafebabe.pochi.birthmarks.uc.UsedClassesExtractorBuilder;

public class ExtractorBuilders extends AbstractTaskBuilders<BirthmarkType, ExtractorBuilder>{
    public ExtractorBuilders() {
        super(ExtractorBuilder.class);
        register();
    }

    private void register() {
        register(new UsedClassesExtractorBuilder());
        kgramStream().forEach(this::register);
    }

    private Stream<ExtractorBuilder> kgramStream(){
        return IntStream.rangeClosed(1, 6)
                .mapToObj(KGramBasedExtractorBuilder::new);
    }
}
