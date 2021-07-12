package jp.cafebabe.pochicmd;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Arguments implements Serializable {
    @Option(names={"-c", "--classpath"}, description="specifies the classpath for Groovy (JVM)")
    private List<String> classpaths = new ArrayList<>();

    @Option(names={"-C", "--config-file"}, description="specifies the configuration file.")
    private String configFile;

    @Option(names={"-e", "--expression"}, description="specifies one line script.")
    private String expression;

    @Option(names={"-w", "--working-dir"}, description="specifies the working directory.")
    private String directory;

    @Option(names={"-v", "--verbose"}, description="sets as verbose mode.")
    private boolean verbose;

    @Option(names={"-h", "--help"}, usageHelp = true, description="prints this message.")
    private boolean helpFlag;

    @Parameters(paramLabel="SCRIPT_FILE [ARGV...]", description="specifies the script files and their arguments.")
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

    public static Arguments parse(String[] args) {
        Arguments arguments = new Arguments();
        new CommandLine(arguments).parseArgs(args);
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
