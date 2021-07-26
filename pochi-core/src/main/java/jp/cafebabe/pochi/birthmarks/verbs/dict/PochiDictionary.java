package jp.cafebabe.pochi.birthmarks.verbs.dict;

import jp.cafebabe.pochi.birthmarks.verbs.EnglishDictionary;

import java.util.*;

class PochiDictionary implements EnglishDictionary {
    private List<String> verbs = Arrays.asList("init", "to", "new", "initialize");
    private Map<String, String> dict = new HashMap<>();

    PochiDictionary() {
        dict.put("init", "initialize");
    }

    @Override
    public boolean isVerb(String term) {
        return verbs.contains(term);
    }

    @Override
    public Optional<String> lemmatize(String term) {
        if(verbs.contains(term))
            return Optional.of(dict.getOrDefault(term, term));
        return Optional.empty();
    }
}
