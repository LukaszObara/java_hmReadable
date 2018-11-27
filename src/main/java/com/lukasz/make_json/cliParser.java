package com.lukasz.make_json;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;


public class cliParser {
    public static Options generateArguments(){
        final Options options = new Options();
        final Option input = Option.builder("i")
            .desc("The input path")
            .hasArg(true)
            .longOpt("input")
            .build();

        options.addOption(input);

        return options;
    }


    public static void generateCommandLine(final Options options, final String[] args){
        final CommandLineParser parser = new DefaultParser();
        final HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            formatter.printHelp("utility-name", options);
        }
    } 
}