package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class txtFileHandler {
    public static List<String> readFile(String path){
        List<String> res = null;
        try {
            res = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getMessage());
        }
        return res;
    }
}
