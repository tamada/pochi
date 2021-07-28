package jp.cafebabe.pochi.birthmarks.verbs;

import jp.cafebabe.pochi.birthmarks.verbs.dict.DefaultEnglishDictionary;

import java.util.Optional;

public interface EnglishDictionary {
    /**
     * This method returns true if the given term is the verb.
     *
     * @param term
     * @return
     */
    boolean isVerb(String term);

    /**
     * Convert the given word to lemma word.
     * @param term
     * @return
     */
    Optional<String> lemmatize(String term);

    static EnglishDictionary defaultInstance() {
        return new DefaultEnglishDictionary();
    }
}
