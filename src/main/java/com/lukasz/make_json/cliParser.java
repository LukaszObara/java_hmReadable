package com.lukasz.make_json;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

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

    public static void generateCommandLine(){} 
}