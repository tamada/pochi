package jp.cafebabe.birthmarks.rules;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.regex.Pattern;

public enum Position {
    PREFIX((pattern, string) -> string.startsWith(pattern)),
    SUFFIX((pattern, string) -> string.endsWith(pattern)),
    EXACT(Objects::equals),
    REGEX(Pattern::matches);

    private BiPredicate<String, String> predicate;

    Position(BiPredicate<String, String> predicate){
        this.predicate = predicate;
    }

    public boolean match(Snippet snippet, String string){
        String value = snippet.value();
        return predicate.test(value, string);
    }
}
