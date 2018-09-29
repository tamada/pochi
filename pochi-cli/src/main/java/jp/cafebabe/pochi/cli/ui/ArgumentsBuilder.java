package jp.cafebabe.pochi.cli.ui;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class ArgumentsBuilder {
    public Arguments build(String[] args){
        Arguments arguments = new Arguments();
        parseArgs(arguments, args);
        return arguments;
    }

    public void parseArgs(Arguments arguments, String[] args){
        parseImpl(new CmdLineParser(arguments), arguments, args);
    }
    
    private void parseImpl(CmdLineParser parser, Arguments arguments, String[] args){
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            arguments.printHelp(parser);
        }
    }
}
