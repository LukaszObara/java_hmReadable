package com.lukasz.make_json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.io.FileUtils;
// import org.apache.commons.cli.Parser;
import org.json.JSONObject;


public class App {
    public static List<String> getJsons(String loc) throws IOException {
        List<String> filesInFolder = Files.walk(Paths.get(loc))
                                        .filter(Files::isRegularFile)
                                        .map(Path::toString)
                                        .filter(e -> e.endsWith("json"))
                                        .collect(Collectors.toList());

        return filesInFolder;
    }


    public static void parseJson(String loc) throws IOException {
        InputStream file = new FileInputStream(new File(loc));
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(file));
        String data = bufferReader.lines()
            .collect(Collectors.joining("\n"));
        System.out.println(data);
        // JSONObject jsonObject = new JSONObject(new FileReader(data));
        // System.out.println(jsonObject);
        // System.out.println(jsonObject.get("index"));
    }

    public static void main(String[] args) throws IOException {
        Options options = cliParser.generateArguments();
        CommandLine cmd = cliParser.generateCommandLine(options, args);

        String loc = "/home/lukasz.obara/Lukasz";
        String locFiles = cmd.getOptionValue("input", loc);
        
        // getJsons(locFiles);
        List<String> filesInFolder = getJsons(locFiles);
        
        for (String file : filesInFolder){
            // System.out.println(file);
            parseJson(file);
            System.out.println("--------");
        }
    }
}
