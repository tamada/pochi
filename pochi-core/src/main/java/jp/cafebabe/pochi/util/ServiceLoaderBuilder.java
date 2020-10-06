package jp.cafebabe.pochi.util;

import io.vavr.control.Try;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;
import java.util.stream.Stream;

public class ServiceLoaderBuilder {
    public <T> ServiceLoader<T> build(Class<T> clazz) {
        return build(clazz, clazz.getClassLoader());
    }

    public <T> ServiceLoader<T> build(Class<T> clazz, ClassLoader loader) {
        return load(clazz, loader)
                .getOrElse(() -> new ServiceLoader<>(Stream.empty()));
    }
    
    private <T> Try<ServiceLoader<T>> load(Class<T> clazz, ClassLoader loader) {
        String path = "META-INF/services/" + clazz.getName();
        return loadPath(path, loader)
                .map(url -> loadFromUrl(clazz, url))
                .orElseGet(() -> Try.failure(new IOException()));
    }

    private <T> Try<ServiceLoader<T>> loadFromUrl(Class<T> clazz, URL url) {
        return Try.withResources(() -> new BufferedReader(new InputStreamReader(url.openStream(), "utf-8")))
                .of(in -> new ServiceLoader<T>(in.lines()));
    }

    private Optional<URL> loadPath(String path, ClassLoader loader){
        URL url = loader.getResource(path);
        return Optional.ofNullable(url);
    }
}
