package jp.cafebabe.pochi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import jp.cafebabe.birthmarks.entities.Metadata;

public class FailedSources {
    private List<Metadata> list = new ArrayList<>();

    public void add(Metadata source){
        list.add(source);
    }

    public Stream<Metadata> stream(){
        return list.stream();
    }
}
