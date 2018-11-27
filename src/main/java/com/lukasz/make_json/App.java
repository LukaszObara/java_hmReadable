package com.lukasz.make_json;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;


public class App {
    public static void main(String[] args) {
        String loc;

        Options options = cliParser.generateArguments();
        CommandLine cmd = cliParser.generateCommandLine(options, args);

        System.out.println("The value of " + cmd.getOptionValue("input", "default_value"));
    }
}
