package com.github.ebis.scripts;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ebis.config.Configuration;

public class Initializer {
    private static URL defaultResource(){
        return Initializer.class
                .getResource("/resources/config.json");
    }
    private Configuration context;

    public Initializer(){
        this(Optional.empty());
    }

    public Initializer(Optional<URL> url){
        this(url.orElseGet(() -> defaultResource()));
    }

    private Initializer(URL url){
        try{ initialize(url); }
        catch(IOException e){ }
    }

    public Configuration configuration(){
        return context;
    }

    private void initialize(URL url) throws IOException{
        try(Reader in = new InputStreamReader(url.openStream())){
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
            context = mapper.readValue(in, Configuration.class);
        }
    }
}
