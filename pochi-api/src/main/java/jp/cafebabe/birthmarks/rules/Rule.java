package jp.cafebabe.birthmarks.rules;

import java.util.StringJoiner;

public class Rule {
    private Position position;
    private Snippet snippet;

    public Rule(Position position, Snippet snippet){
        this.position = position;
        this.snippet = snippet;
    }

    public Rule(String position, String snippet){
        this(Position.valueOf(position.toUpperCase()),
                new Snippet(snippet));
    }

    private String position(){
        return position.name();
    }

    private String snippet(){
        return snippet.toString();
    }

    public boolean match(String string){
        return position.match(snippet, string);
    }

    public String toJson() {
        return String.format("{\"type\":\"%s\",\"pattern\":\"%s\"}", position(), snippet());
    }

    @Override
    public String toString(){
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(position());
        joiner.add(snippet());
        return joiner.toString();
    }
}
