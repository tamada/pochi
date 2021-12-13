import jp.cafebabe.kunai.sink.DataSink;
import jp.cafebabe.kunai.sink.factories.DataSinkFactory;
import jp.cafebabe.kunai.source.DataSource;
import jp.cafebabe.kunai.source.factories.DataSourceFactory;

/**
 * <p>
 *   The module <code>jp.cafebabe.kunai</code> handles the reading and writing the class files.
 * </p><p>
 * The reading supports from local files, directories, jar/war files as the {@link DataSource}.
 * The writing supports to local files, directories, jar files by representing {@link DataSink}.
 * </p><p>
 * The below program is an example program for copying reading source to the destination (it assumes local files).
 * </p>
 *
 * <pre>
 * public void copy(String from, String to) throws Exception {
 *     try({@link DataSource} source = {@link DataSourceFactory}.instance().build(Paths.get(from));
 *             {@link DataSink} sink = {@link DataSinkFactory}.instance().create(Paths.get(to))){
 *         copy(source, sink);
 *     }
 * }
 * public void copy({@link DataSource} source, {@link DataSink} sink) throws Exception {
 *     sink.consume(source);
 * }
 * </pre>
 *
 * @author Haruaki Tamada
 */
module jp.cafebabe.kunai {
    requires transitive org.objectweb.asm;
    requires io.vavr;

    exports jp.cafebabe.kunai.entries;
    exports jp.cafebabe.kunai.sink;
    exports jp.cafebabe.kunai.sink.factories;
    exports jp.cafebabe.kunai.source;
    exports jp.cafebabe.kunai.source.factories;
}
