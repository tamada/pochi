module jp.cafebabe.kunai {
    requires transitive org.objectweb.asm;
    requires io.vavr;

    exports jp.cafebabe.kunai.entries;
    exports jp.cafebabe.kunai.sink;
    exports jp.cafebabe.kunai.source;
    exports jp.cafebabe.kunai.source.factories;
}