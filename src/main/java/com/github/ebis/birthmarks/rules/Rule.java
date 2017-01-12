package com.github.ebis.birthmarks.rules;

import java.util.StringJoiner;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Rule {
    private Position position;
    private Snippet snippet;

    public Rule(Position position, Snippet snippet){
        this.position = position;
        this.snippet = snippet;
    }

    @JsonCreator
    public Rule(@JsonProperty("type") String position,
            @JsonProperty("pattern") String snippet){
        this(Position.valueOf(
                position.toUpperCase()),
                new Snippet(snippet));
    }

    @JsonProperty("type")
    private String position(){
        return position.name();
    }

    @JsonProperty("pattern")
    private String snippet(){
        return snippet.toString();
    }

    public boolean match(String string){
        return position.match(snippet, string);
    }

    @Override
    public String toString(){
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(position());
        joiner.add(snippet());
        return joiner.toString();
    }
}
