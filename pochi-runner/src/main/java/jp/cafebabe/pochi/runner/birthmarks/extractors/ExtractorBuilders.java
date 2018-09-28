package jp.cafebabe.pochi.runner.birthmarks.extractors;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import jp.cafebabe.pochi.birthmarks.AbstractTaskBuilders;
import jp.cafebabe.pochi.birthmarks.entities.BirthmarkType;
import jp.cafebabe.pochi.birthmarks.extractors.ExtractorBuilder;
import jp.cafebabe.pochi.runner.birthmarks.kgram.KGramBasedExtractorBuilder;
import jp.cafebabe.pochi.runner.birthmarks.uc.UsedClassesExtractorBuilder;

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
