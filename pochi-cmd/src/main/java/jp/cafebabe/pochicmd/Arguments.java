package jp.cafebabe.pochicmd;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Arguments implements Serializable {
    @Option(name="-c", aliases="--classpath", usage="specifies the classpath for Groovy (JVM)")
    private List<String> classpaths = new ArrayList<>();

    @Option(name="-C", aliases="--config-file", usage="specifies the configuration file.")
    private String configFile;

    @Option(name="-e", aliases="--expression", usage="specifies one line script.")
    private String expression;

    @Option(name="-w", aliases="--working-dir", usage="specifies the working directory.")
    private String directory;

    @Option(name="-v", aliases="--verbose", usage="sets as verbose mode.")
    private boolean verbose;

    @Option(name="-h", aliases="--help", usage="prints this message.")
    private boolean helpFlag;

    @Argument(metaVar="SCRIPT_FILE [ARGV...]")
    private List<String> arguments = new ArrayList<>();

    public String expression() {
        return expression;
    }

    public Stream<String> arguments() {
        return arguments.stream();
    }

    public Stream<String> classpaths() {
        return classpaths.stream();
    }

    public ProcessBuilder setupProcessBuilder(ProcessBuilder builder) {
        if(configFile != null) {
            builder.environment().put(Main.CONFIG_NAME, configFile);
        }
        if(directory != null && Files.isDirectory(Paths.get(directory))) {
            builder = builder.directory(new File(directory));
        }
        return builder;
    }

    public boolean isHelp() {
        return helpFlag;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public static Arguments parse(String[] args) throws CmdLineException {
        Arguments arguments = new Arguments();
        CmdLineParser parser = new CmdLineParser(arguments);
        parser.parseArgument(args);
        return arguments;
    }

    public Runner.Mode findMode() {
        if(helpFlag) {
            return Runner.Mode.HelpMode;
        }
        else if(arguments.size() > 0){
            return Runner.Mode.ScriptFile;
        }
        else if(expression != null) {
            return Runner.Mode.OneLineExpression;
        }
        return Runner.Mode.Interactive;
    }
}
