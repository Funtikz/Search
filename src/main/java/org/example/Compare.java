package org.example;

import java.util.*;

public class Compare {
    private final List<String> listTxt;
    private final Map<String[],String> mapCsv;

    public Compare(List<String> listTxt, Map<String[], String> mapCsv) {
        this.listTxt = listTxt;
        this.mapCsv = mapCsv;
    }

    public Result compareWord(){
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
        int currentPosition = -1;
        for (var line : changedList){   //Проходимся по листу запросов
            currentPosition++;
            long start = System.currentTimeMillis();
            for (var dataCsv : mapCsv.keySet()){ // Проходимся по данным из CSV файла
                int count = 0;
                for (var word : dataCsv){ //  Проходимя по каждому слову из строки файла
                    String last = String.valueOf(word.charAt(word.length()-1));
                    if (glasn.contains(last)){
                        word = word.substring(0,word.length()-1);
                    }
                    if (line.contains(word)){
                        count++;
                    }
                }
                int countWords = dataCsv.length;
                String maybe = mapCsv.get(dataCsv);
                if ((double) count /countWords >= 0.7){ //Если у меня запрос совпадает с информацией на 70+ % добавляем
                    if (result.get(listTxt.get(currentPosition)) == null) {
                        List<String> list = new ArrayList<>();
                        list.add(mapCsv.get(dataCsv));
                        result.put(listTxt.get(currentPosition), list);
                        resultClass.setMapResult(result);

                    }else {
                        List<String> strings = result.get(listTxt.get(currentPosition));
                        strings.add(mapCsv.get(dataCsv));
                        result.put(listTxt.get(currentPosition),strings);
                        Map<String, List<String>> mapResult = resultClass.getMapResult();
                        mapResult.put(listTxt.get(currentPosition),strings);
                    }
                }
            }
            long stop = System.currentTimeMillis();
            listOneTimeOperation.add(String.valueOf(stop-start)); // Добавляю время 1 операции в список
        }
        resultClass.setTimeOneOperation(listOneTimeOperation);
        return resultClass;
    }
}
