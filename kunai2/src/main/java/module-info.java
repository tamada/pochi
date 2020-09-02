module jp.cafebabe.kunai {
    exports jp.cafebabe.kunai.entries;
    exports jp.cafebabe.kunai.sink;
    exports jp.cafebabe.kunai.source;
    exports jp.cafebabe.kunai.source.factories;
    requires jp.cafebabe.nasubi;
    requires transitive org.objectweb.asm;
}