package jp.cafebabe.pochi.kunai.source;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import jp.cafebabe.pochi.kunai.entries.Entry;

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
