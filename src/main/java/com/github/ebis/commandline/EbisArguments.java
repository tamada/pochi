package com.github.ebis.commandline;

import java.util.ArrayList;
import java.util.List;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/**
 * 
 * 
 * @author Haruaki Tamada
 */
public class EbisArguments {
    @Option(name = "-b", usage = "Specify birthmarks", metaVar = "BIRTHMARKS...")
    private List<String> birthmarks = new ArrayList<>();

    @Option(name = "-c", usage = "Specify the command for Ebis", metaVar = "COMMAND")
    private String command;

    @Argument(metaVar = "TARGETS...", multiValued = true, required = true)
    private List<String> targets = new ArrayList<>();

    public void parseOptions(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        parser.getProperties().withUsageWidth(80);

        try {
            parser.parseArgument(args);
            if (targets.isEmpty()) {
                throw new CmdLineException(parser, "No argument was given.");
            }
        } catch (CmdLineException e) {

        }
    }
}
