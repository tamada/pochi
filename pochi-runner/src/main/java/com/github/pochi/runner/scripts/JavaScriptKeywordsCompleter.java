package com.github.pochi.runner.scripts;

import org.jline.reader.impl.completer.StringsCompleter;

public class JavaScriptKeywordsCompleter extends StringsCompleter {
    public JavaScriptKeywordsCompleter(){
        super("break", "case", "catch", "continue", "debugger", "default", "delete", "do",
              "else", "finally", "for", "function", "if", "in", "instanceof", "new", "return",
              "switch", "this", "throw", "try", "typeof", "var", "void", "while", "with",
              "class", "enum", "export", "extends", "import", "super", "implements", "interface",
              "let", "package", "private", "protected", "public");
    }
}
