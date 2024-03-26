package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CsvReader {
    private final String path;

    public CsvReader(String path) {
        this.path = path;
    }


    public Map<String, String> readData() {
        Map<String, String> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll(",", "")
                        .replaceAll("\\.", "")
                        .replaceAll("\\?", "")
                        .replaceAll("!", "")
                        .toLowerCase();
                String[] strings = line.split("\\|");
                if (strings.length >= 3) {
                    map.put( strings[2].replaceAll("\"", ""), strings[0].replaceAll("\"", ""));
                } else {

                }
            }
        } catch (IOException e) {
            System.err.println("Не верный путь к CSV файлу: " + e.getMessage());
        }
        return map;
    }

}

