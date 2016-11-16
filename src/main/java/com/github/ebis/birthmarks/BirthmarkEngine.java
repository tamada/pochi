package com.github.ebis.birthmarks;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.github.ebis.Context;
import com.github.ebis.birthmarks.extractor.Extractor;
import com.github.kunai.entries.ClassName;
import com.github.kunai.entries.Entry;
import com.github.kunai.source.DataSource;

public class BirthmarkEngine<T> {
    private BirthmarkService<T> service;
    private Context context;

    public BirthmarkEngine(BirthmarkService<T> service, Context context){
        this.service = service;
        this.context = context;
    }

    public ExtractionResults<T> extract(DataSource source) throws BirthmarkException{
        Extractor<T> extractor = service.extractor();
        List<ExtractionResult<T>> list = source.stream()
                .filter(entry -> entry.isClass())
                .map(entry -> extract(extractor, entry, context))
                .filter(optional -> optional.isPresent())
                .map(optional -> optional.get())
                .collect(Collectors.toList());

        return new ExtractionResults<T>(list);
    }

    public ComparisonResults<T> compare(ExtractionResults<T> rs){
        return new ComparisonResults<T>(rs, service.computer());
    }

    private Optional<ExtractionResult<T>> extract(Extractor<T> extractor, Entry entry, Context context){
        try {
            ClassName cn = entry.getClassName();
            Identifier identifier = new Identifier(cn, entry.loadFrom());
            Birthmark<T> birthmark = extractor.extract(entry, context);
            return Optional.of(new ExtractionResult<T>(identifier, birthmark));
        } catch (BirthmarkException e) {
        }
        return Optional.empty();
    }
}
