package jp.cafebabe.pochi.birthmarks.verbs;

import jp.cafebabe.pochi.birthmarks.verbs.dict.DefaultEnglishDictionary;

import java.util.Optional;

public interface EnglishDictionary {
    /**
     * This method returns true if the given term is the verb.
     *
     * @param term is verb?
     * @return if the given term is verb, then return true, otherwise false.
     */
    boolean isVerb(String term);

    /**
     * Convert the given word to lemma word.
     * @param term to lemma word
     * @return lemma word of term
     */
    Optional<String> lemmatize(String term);

    static EnglishDictionary defaultInstance() {
        return new DefaultEnglishDictionary();
    }
}
