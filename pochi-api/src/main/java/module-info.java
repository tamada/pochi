module jp.cafebabe.pochi.api {
   exports jp.cafebabe.pochi.birthmarks;
   exports jp.cafebabe.pochi.birthmarks.comparators;
   exports jp.cafebabe.pochi.birthmarks.config;
   exports jp.cafebabe.pochi.birthmarks.entities;
   exports jp.cafebabe.pochi.birthmarks.extractors;
   exports jp.cafebabe.pochi.birthmarks.pairs;

   requires transitive jp.cafebabe.pochi.kunai;
   requires transitive jp.cafebabe.pochi.nasubi;
   requires transitive io.vavr;
   requires java.logging;
   requires com.fasterxml.jackson.databind;
}
