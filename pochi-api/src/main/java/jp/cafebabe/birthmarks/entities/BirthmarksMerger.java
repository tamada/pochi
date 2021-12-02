package jp.cafebabe.birthmarks.entities;

import io.vavr.control.Try;
import jp.cafebabe.birthmarks.utils.LongestCommonSubstring;
import jp.cafebabe.kunai.entries.ClassName;

import java.net.URI;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class BirthmarksMerger {
    public <T> Optional<Birthmark<T>> unify(Birthmarks<T> birthmarks) {
        Optional<Elements<T>> elements = mergeElements(birthmarks);
        Metadata metadata = mergeMetadata(birthmarks);
        return elements.map(e -> new Birthmark<>(metadata, e));
    }

    private <T> Metadata mergeMetadata(Birthmarks<T> birthmarks) {
        String location = findCommonLocation(birthmarks.stream());
        return constructMetadata(location, findType(birthmarks));
    }

    private <T> Optional<BirthmarkType> findType(Birthmarks<T> birthmarks) {
        return birthmarks.stream().map(b -> b.type())
                .collect(Collectors.reducing((a, b) -> a));
    }

    private Metadata constructMetadata(String location, Optional<BirthmarkType> type) {
        Try<URI> tryUri = Try.of(() -> new URI(location));
        Optional<URI> uri = tryUri.toJavaOptional();
        Optional<ClassName> name = uri.map(u -> new ClassName(findBaseName(u)));
        return new Metadata(name.orElseGet(() -> new ClassName("<merged>")), uri.get(), type.get());
    }

    private String findBaseName(URI uri) {
        String path = uri.toString();
        int index = path.lastIndexOf('/');
        if(index >= 0)
            return path.substring(index + 1);
        return path;
    }

    private <T> String findCommonLocation(Stream<Birthmark<T>> stream) {
        Optional<String> location = stream.map(birthmark -> birthmark.metadata())
                .map(m -> m.location().toString())
                .collect(Collectors.reducing((s1, s2) -> LongestCommonSubstring.of(s1, s2)));
        return location.orElseGet(() -> "");
    }

    private <T> Optional<Elements<T>> mergeElements(Birthmarks<T> birthmarks) {
        return birthmarks.stream()
                .map(birthmark -> birthmark.elements())
                .collect(Collectors.reducing((a, b) -> a.merge(b)));
    }

    public static <T> Optional<Birthmark<T>> unifyTo(Birthmarks<T> birthmarks) {
        return new BirthmarksMerger().unify(birthmarks);
    }
}
