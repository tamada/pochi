module jp.cafebabe.kunai {
    requires transitive jp.cafebabe.nasubi;
    requires transitive org.objectweb.asm;

    exports jp.cafebabe.kunai.entries;
    exports jp.cafebabe.kunai.sink;
    exports jp.cafebabe.kunai.source;
    exports jp.cafebabe.kunai.source.factories;
}