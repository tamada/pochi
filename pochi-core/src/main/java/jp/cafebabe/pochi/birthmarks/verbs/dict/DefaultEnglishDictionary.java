package jp.cafebabe.pochi.birthmarks.verbs.dict;

import jp.cafebabe.pochi.birthmarks.verbs.EnglishDictionary;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DefaultEnglishDictionary implements EnglishDictionary {
    private List<EnglishDictionary> dictionaries = Arrays
            .asList(new PochiDictionary(), OpenNLPDictionary.defaultInstance());

    @Override
    public boolean isVerb(String term) {
        return dictionaries.stream()
                .anyMatch(dict -> dict.isVerb(term));
    }

    @Override
    public Optional<String> lemmatize(String term) {
        return dictionaries.stream()
                .map(dict -> dict.lemmatize(term))
                .flatMap(value -> value.stream())
                .findFirst();
    }
}
