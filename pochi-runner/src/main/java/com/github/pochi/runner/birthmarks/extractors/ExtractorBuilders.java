package com.github.pochi.runner.birthmarks.extractors;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.github.pochi.birthmarks.AbstractTaskBuilders;
import com.github.pochi.birthmarks.entities.BirthmarkType;
import com.github.pochi.birthmarks.extractors.ExtractorBuilder;
import com.github.pochi.runner.birthmarks.kgram.KGramBasedExtractorBuilder;
import com.github.pochi.runner.birthmarks.uc.UsedClassesExtractorBuilder;

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
