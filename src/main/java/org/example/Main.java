package org.example;


import org.example.Readers.CsvReader;
import org.example.Readers.TxtReader;

import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        UtilityClass utilityClass = new UtilityClass();
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "--data":
                    utilityClass.setPathToCsv(args[i + 1]);
                    break;
                case "--input-file":
                    utilityClass.setPathToTxt(args[i+1]);
                    break;
                case "--output-file":
                    utilityClass.setPathToJson(args[i+1]);
                    break;
                default:
                    System.err.println("Неизвестный аргумент: " + args[i]);
                    System.exit(1);
            }
        }
        long start = System.currentTimeMillis();
        CsvReader csvReader = new CsvReader(utilityClass.getPathToCsv());
        TxtReader txtReader = new TxtReader(utilityClass.getPathToTxt());
        Map<String[], String> stringsCsvData = csvReader.readData();
        List<String> stringsTxtData = txtReader.readData();
        long stop = System.currentTimeMillis();
        Compare compare = new Compare(stringsTxtData,stringsCsvData);
        String initTime = String.valueOf(stop - start);
        Result result = compare.compareWord();
        result.setTimeReadFile(initTime);
        JsonHelper jsonHelper = new JsonHelper(utilityClass.getPathToJson());
        jsonHelper.createOrUpdate(result);
    }
}
