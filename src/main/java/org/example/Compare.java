package org.example;

import java.util.*;

public class Compare {
    private final List<String> listTxt;
    private final Map<String[],String> mapCsv;

    public Compare(List<String> listTxt, Map<String[], String> mapCsv) {
        this.listTxt = listTxt;
        this.mapCsv = mapCsv;
    }

    public Result compareWord() {
        String glasn = "ауоеюяийыэ"; // Проверка на склонения
        List<String> changedList = new ArrayList<>(listTxt);
        changedList = changedList.stream().map(line -> line.replaceAll(",", "") // Убираю лишние символы и привожу к нижнему регистру для сравнения
                        .replaceAll("\\.", "")
                        .replaceAll("\\?", "")
                        .replaceAll("!", "")
                        .toLowerCase())
                .toList();

        Result resultClass = new Result();
        Map<String, List<String>> result = new HashMap<>();
        List<String> listOneTimeOperation = new ArrayList<>();

        for (int i = 0; i < changedList.size(); i++) { //Проходимся по листу запросов
            String line = changedList.get(i);
            long start = System.currentTimeMillis();
            List<String> matchingStrings = new ArrayList<>();
            for (var entry : mapCsv.entrySet()) { // Проходимся по данным из CSV файла
                String[] dataCsv = entry.getKey();
                int count = 0;
                for (String word : dataCsv) { //  Проходимя по каждому слову из строки файла
                    if (!word.isEmpty()) { // Проверяем, что слово не пустое
                        String last = String.valueOf(word.charAt(word.length() - 1));
                        if (glasn.contains(last)) {
                            word = word.substring(0, word.length() - 1);
                        }
                        if (line.contains(word)) {
                            count++;
                        }
                    }
                }
                int countWords = dataCsv.length;
                if ((double) count / countWords >= 0.4) { //Если у меня запрос совпадает с информацией на 40+ % добавляем
                    matchingStrings.add(entry.getValue());
                }
            }
            result.put(listTxt.get(i), matchingStrings);
            long stop = System.currentTimeMillis();
            listOneTimeOperation.add(String.valueOf(stop - start)); // Добавляю время 1 операции в список
        }

        resultClass.setMapResult(result);
        resultClass.setTimeOneOperation(listOneTimeOperation);
        return resultClass;
    }

}
