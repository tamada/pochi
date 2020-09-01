package jp.cafebabe.pochi.birthmarks.config;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.Spliterators;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.cafebabe.pochi.birthmarks.rules.Rule;
import jp.cafebabe.pochi.birthmarks.rules.Rules;

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
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.WARNING, e.getMessage(), e);
        }
    }

    public Configuration configuration() {
        return configuration;
    }

    private void initialize(URL url) throws IOException {
        try (Reader in = new InputStreamReader(url.openStream())) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
            configuration = constructConfig(mapper.readTree(in));
        }
    }

    private Configuration constructConfig(JsonNode node) {
        JsonNode propertiesNode = node.get("properties");
        Map<String, String> props = StreamSupport.stream(Spliterators.spliteratorUnknownSize(propertiesNode.fieldNames(), 0), false)
                .collect(Collectors.toMap(name -> name, name ->propertiesNode.get(name).asText(), (a, b) -> b));
        JsonNode rulesNode = node.get("rules");
        Stream<Rule> stream = IntStream.range(0, rulesNode.size())
                .mapToObj(index -> toRule(rulesNode.get(index)));
        return new Configuration(new Rules(stream), props);
    }

    private Rule toRule(JsonNode node) {
        return new Rule(node.get("type").asText(), node.get("pattern").asText());
    }

    private static URL defaultResource() {
        return ConfigurationBuilder.class
                .getResource("/resources/config.json");
    }
}
