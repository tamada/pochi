package jp.cafebabe.pochi.birthmarks.verbs;

import java.util.Optional;

public class MethodNameUtil {
    private static EnglishDictionary dict = EnglishDictionary.defaultInstance();

    public static Optional<String> verb(String methodName) {
        String verb = extractVerb(methodName);
        Optional<String> lemmatizedVerb = dict.lemmatize(verb);
        if(lemmatizedVerb.map(v -> dict.isVerb(v)).orElse(false))
            return lemmatizedVerb;
        return Optional.empty();
    }

    public static String extractVerb(String name) {
        return new String(name.chars()
                .takeWhile(value -> value >= 'a' && value <= 'z')
                .collect(() -> new StringBuilder(),
                        (sb, value) -> sb.append((char)value),
                        (sb1, sb2) -> sb1.append(sb2)));
    }
}
