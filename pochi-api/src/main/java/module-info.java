module jp.cafebabe.birthmarks {
   requires transitive jp.cafebabe.kunai;
   requires transitive io.vavr;
   requires java.logging;
   requires com.fasterxml.jackson.databind;

   exports jp.cafebabe.birthmarks;
   exports jp.cafebabe.birthmarks.comparators;
   exports jp.cafebabe.birthmarks.config;
   exports jp.cafebabe.birthmarks.entities;
   exports jp.cafebabe.birthmarks.extractors;
   exports jp.cafebabe.birthmarks.pairs;

   uses jp.cafebabe.birthmarks.comparators.ComparatorBuilder;
   uses jp.cafebabe.birthmarks.extractors.ExtractorBuilder;
   uses jp.cafebabe.birthmarks.pairs.PairMatcherBuilder;
}
