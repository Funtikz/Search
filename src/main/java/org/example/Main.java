package org.example;


import java.util.List;
import java.util.Map;
import java.util.Set;


public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        String CsvPath = "C:\\Test2\\INFO5.csv";
        String TxtPath = "C:\\Test2\\TxtInfo.txt";
        CsvReader csvReader = new CsvReader(CsvPath);
        TxtReader txtReader = new TxtReader(TxtPath);
        Map<String, String> stringsCsvData = csvReader.readData();
        List<String[]> stringTxtData  = txtReader.readData();
        Compare compare = new Compare(stringTxtData,stringsCsvData);
        long stop = System.currentTimeMillis();


        Result result = compare.compareWord();
        System.out.println(stop - start);
        System.out.println("r");

    }
}
