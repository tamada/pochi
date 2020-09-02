import jp.cafebabe.birthmarks.comparators.ComparatorBuilder;
import jp.cafebabe.birthmarks.extractors.ExtractorBuilder;
import jp.cafebabe.birthmarks.pairs.PairMatcherBuilder;

module jp.cafebabe.birthmarks {
   exports jp.cafebabe.birthmarks;
   exports jp.cafebabe.birthmarks.comparators;
   exports jp.cafebabe.birthmarks.config;
   exports jp.cafebabe.birthmarks.entities;
   exports jp.cafebabe.birthmarks.extractors;
   exports jp.cafebabe.birthmarks.pairs;

   requires transitive jp.cafebabe.kunai;
   requires transitive jp.cafebabe.nasubi;
   requires transitive io.vavr;
   requires java.logging;
   requires com.fasterxml.jackson.databind;

   uses ComparatorBuilder;
   uses ExtractorBuilder;
   uses PairMatcherBuilder;
}
