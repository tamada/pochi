package jp.cafebabe.nasubi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;
import java.util.stream.Stream;

public class ServiceLoaderBuilder {
    public <T> ServiceLoader<T> build(Class<T> clazz){
        return build(clazz, clazz.getClassLoader());
    }
    public <T> ServiceLoader<T> build(Class<T> clazz, ClassLoader loader){
        return Exceptions.map(clazz, loader, (c, l) -> load(c, l))
                .orElseGet(() -> new ServiceLoader<T>(Stream.empty()));
    }
    
    private <T> ServiceLoader<T> load(Class<T> clazz, ClassLoader loader) throws IOException{
        String path = "META-INF/services/" + clazz.getName();
        Optional<URL> url = loadPath(path, loader);
        return loadFromUrl(url.orElseThrow(() -> new IOException()));
    }

    private <T> ServiceLoader<T> loadFromUrl(URL url) throws IOException{
        try(BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"))){
            return new ServiceLoader<T>(in.lines());
        }
    }

    private Optional<URL> loadPath(String path, ClassLoader loader){
        URL url = loader.getResource(path);
        return Optional.ofNullable(url);
    }
}
