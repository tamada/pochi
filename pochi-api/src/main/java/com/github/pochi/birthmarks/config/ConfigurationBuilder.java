package com.github.pochi.birthmarks.config;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigurationBuilder {
    private Configuration configuration;

    public ConfigurationBuilder() {
        this(Optional.empty());
    }

    public ConfigurationBuilder(Optional<URL> url) {
        this(url.orElseGet(ConfigurationBuilder::defaultResource));
    }

    private ConfigurationBuilder(URL url) {
        try {
            initialize(url);
        } catch (IOException e) {
            // LogHelper.warn(this, e);
        }
    }

    public Configuration configuration() {
        return configuration;
    }

    private void initialize(URL url) throws IOException {
        try (Reader in = new InputStreamReader(url.openStream())) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
            configuration = mapper.readValue(in, Configuration.class);
        }
    }

    private static URL defaultResource() {
        return ConfigurationBuilder.class
                .getResource("/resources/config.json");
    }
}
