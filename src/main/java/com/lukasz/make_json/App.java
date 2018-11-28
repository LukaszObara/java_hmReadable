package com.lukasz.make_json;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.json.JSONObject;


public class App {
    public static List<File> getJsons(String loc) throws IOException {
        List<File> filesInFolder = Files.walk(Paths.get(loc))
                                        .filter(Files::isRegularFile)
                                        .map(Path::toFile)
                                        .collect(Collectors.toList());
        
        return filesInFolder;
    }

    
    public static void parseJson(File loc){
        JSONObject jsonObject = new JSONObject(loc);
        System.out.println(jsonObject.get("index"));
    }

    public static void main(String[] args) throws IOException {
        Options options = cliParser.generateArguments();
        CommandLine cmd = cliParser.generateCommandLine(options, args);

        String loc = "/home/lukasz/Documents/data";
        String locFiles = cmd.getOptionValue("input", loc);
        
        List<File> filesInFolder = getJsons(locFiles);
        
        for (File file : filesInFolder){
            parseJson(file);
        }
        // filesInFolder.stream()
        //     .forEach(System.out::println);
        // System.out.println(filesInFolder);
    }
}
