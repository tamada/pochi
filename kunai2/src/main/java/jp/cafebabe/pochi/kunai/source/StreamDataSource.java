package jp.cafebabe.pochi.kunai.source;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import jp.cafebabe.pochi.kunai.entries.Entry;

/**
 * An implemented class of DataSource with Stream.
 * 
 * @author Haruaki Tamada
 */
public class StreamDataSource implements DataSource {
    private List<Entry> list;

    public StreamDataSource(Stream<Entry> stream) {
        list = stream.collect(Collectors.toList());
    }
    
    public Stream<Entry> stream() {
        return list.stream();
    }

    public void close() throws IOException {
    }
}
