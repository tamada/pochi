package jp.cafebabe.kunai.source;

import java.io.IOException;
import java.util.function.Predicate;
import java.util.stream.Stream;

import jp.cafebabe.kunai.entries.Entry;

/**
 * An implemented class of DataSource with Stream.
 * 
 * @author Haruaki Tamada
 */
class FilteredDataSource extends AbstractDataSource {
    private DataSource source;
    private Predicate<Entry> predicate;

    public FilteredDataSource(DataSource source, Predicate<Entry> predicate) {
        super(source.base());
        this.source = source;
        this.predicate = predicate;
    }
    
    public Stream<Entry> stream() {
        return source.stream()
            .filter(predicate);
    }

    public void close() throws IOException {
        source.close();
    }
}
