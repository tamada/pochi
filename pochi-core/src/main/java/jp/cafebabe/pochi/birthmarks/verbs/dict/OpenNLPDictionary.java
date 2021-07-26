package jp.cafebabe.pochi.birthmarks.verbs.dict;

import io.vavr.CheckedFunction1;
import io.vavr.control.Try;
import jp.cafebabe.pochi.Pochi;
import jp.cafebabe.pochi.birthmarks.verbs.EnglishDictionary;
import opennlp.tools.lemmatizer.DictionaryLemmatizer;
import opennlp.tools.lemmatizer.Lemmatizer;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTagger;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.postag.TagDictionary;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

class OpenNLPDictionary implements EnglishDictionary {
    private POSTagger tagger;
    private TagDictionary dict;
    private Lemmatizer lemmatizer;

    private OpenNLPDictionary(POSModel model, Lemmatizer lemmatizer) {
        this.tagger = new POSTaggerME(model);
        this.dict = model.getFactory().getTagDictionary();
        this.lemmatizer = lemmatizer;
    }

    public String[] tags(String term) {
        return dict.getTags(term);
    }

    private boolean checkVerb(String[] tags) {
        if(tags == null)
            return false;
        return Arrays.stream(tags)
                .anyMatch(tag -> tag.startsWith("V"));
    }

    public boolean isVerb(String verb) {
        return checkVerb(tags(verb));
    }

    public Optional<String> lemmatize(String term) {
        String[] tags = tagger.tag(new String[] { term });
        String lemmatizedTerm = lemmatizer.lemmatize(new String[] { term }, tags)[0];
        if(Objects.equals(lemmatizedTerm, "O"))
            return Optional.empty();
        return Optional.of(lemmatizedTerm);
    }

    static EnglishDictionary defaultInstance() {
        Path data = Pochi.home().resolve("data");
        return new OpenNLPDictionary(build(data.resolve("en-pos-maxent.bin"), posModelBuilder()),
                build(data.resolve("en-lemmatizer.dict"), lemmatizerBuilder()));
    }

    private static <T> T build(Path path, CheckedFunction1<InputStream, T> mapper) {
        return Try.withResources(() -> Files.newInputStream(path))
                .of(in -> mapper.apply(in))
                .getOrElseThrow(throwable -> new InternalError(throwable));
    }

    private static CheckedFunction1<InputStream, Lemmatizer> lemmatizerBuilder() {
        return (in) -> new DictionaryLemmatizer(in);
    }

    private static CheckedFunction1<InputStream, POSModel> posModelBuilder() {
        return in -> new POSModel(in);
    }
}
