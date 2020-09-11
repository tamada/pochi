package jp.cafebabe.kunai.source;

import java.net.URI;
import java.util.Comparator;

import jp.cafebabe.kunai.entries.Entry;

public class EntryComparator implements Comparator<Entry>{
    @Override
    public int compare(Entry o1, Entry o2) {
        URI uri1 = o1.loadFrom();
        URI uri2 = o2.loadFrom();

        return uri1.compareTo(uri2);
    }

}
