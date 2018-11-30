package com.lukasz.make_json;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.json.JSONObject;
import org.json.JSONTokener;


public class App {
    public static List<String> getJsons(String loc) throws IOException {
        List<String> filesInFolder = Files.walk(Paths.get(loc))
                                        .filter(Files::isRegularFile)
                                        .map(Path::toString)
                                        .filter(e -> e.endsWith("json"))
                                        .collect(Collectors.toList());

        return filesInFolder;
    }


    public static String parseJson(String loc) throws IOException {
        InputStream file = new FileInputStream(new File(loc));
        // BufferedReader bufferReader = new BufferedReader(new InputStreamReader(file));
        // String data = bufferReader.lines()
        //     .collect(Collectors.joining("\n"));

        // JSONObject jsonObject = new JSONObject(data);
        // bufferReader.close();
        JSONObject jsonObject = new JSONObject(new JSONTokener(file));
        file.close();

        return jsonObject.toString(4);
    }

    public static void main(String[] args) throws IOException {
        Options options = cliParser.generateArguments();
        CommandLine cmd = cliParser.generateCommandLine(options, args);

        String loc = "/home/lukasz/Documents/data";
        String locFiles = cmd.getOptionValue("input", loc);
        
        List<String> filesInFolder = getJsons(locFiles);
        
        for (String file : filesInFolder){
            String json = parseJson(file);

            try (PrintWriter out = new PrintWriter(file)) {
                out.println(json);
            }
        }
    }
}
