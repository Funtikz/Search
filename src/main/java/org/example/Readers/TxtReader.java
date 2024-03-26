package org.example.Readers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TxtReader {
    public final String path;

    public TxtReader(String path) {
        this.path = path;
    }

    public List<String> readData(){
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ( (line = reader.readLine()) != null ){

                list.add(line);
            }
        }
        catch (IOException e){
            System.err.println("Не верный путь к txt файлу!" + e.getMessage());
        }
        return  list;
    }

}
